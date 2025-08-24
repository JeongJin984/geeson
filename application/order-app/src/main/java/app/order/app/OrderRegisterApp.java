package app.order.app;

import app.order.command.OrderRegisterCommand;
import app.order.port.OrderEventPublisherPort;
import domain.order.entity.*;
import app.order.exception.CustomerNotFoundException;
import app.order.exception.ShippingAddressNotFoundException;
import domain.order.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import support.messaging.command.OrderStartPayload;
import support.uuid.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderRegisterApp {
    private final ProductOrderRepository productOrderRepository;
    private final CustomerRepository customerRepository;
    private final ShippingAddressRepository shippingAddressRepository;
    private final UuidGenerator uuidGenerator;

    private final OrderEventPublisherPort orderEventPublisherPort;

    public ProductOrderJpaEntity registerOrder(OrderRegisterCommand command) {
        CustomerJpaEntity customer = customerRepository.findByCustomerId(command.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));
        ShippingAddressJpaEntity shippingAddress = shippingAddressRepository.findByShippingAddressId(command.shippingAddressId())
                .orElseThrow(() -> new ShippingAddressNotFoundException("shipping address not found"));

        ProductOrderJpaEntity productOrderEntity = new ProductOrderJpaEntity(
            uuidGenerator.nextId(),
            customer,
            command.getTotalPrice(),
            "ORDERED",
            shippingAddress
        );

        productOrderEntity.setOrderItem(
            command.items().stream()
                .map(item -> new OrderItemJpaEntity(
                    uuidGenerator.nextId(),
                    item.productId(),
                    item.quantity(),
                    item.unitPrice(),
                    command.getTotalPrice()
                )).toList()
        ); // 양방향 세팅

        productOrderEntity.registerPayment(
            PaymentRequestJpaEntity.builder()
                .paymentId(uuidGenerator.nextId())
                .orderId(productOrderEntity.getOrderId())
                .order(productOrderEntity)
                .amount(productOrderEntity.getTotalPrice())
                .paymentMethod(String.valueOf(command.paymentMethodId()))
                .transactionId(command.paymentKey())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build()
        ); // 등록

        // 이제 모두 설정된 상태로 한번에 save
        productOrderRepository.save(productOrderEntity); // cascade 설정되어 있어야 제대로 동작

        orderEventPublisherPort.publishOrderCreated(new OrderStartPayload(
            String.valueOf(productOrderEntity.getOrderId()),
            String.valueOf(customer.getCustomerId()),
            productOrderEntity.getPayment().getPaymentMethod(),
            productOrderEntity.getPayment().getTransactionId(),
            String.valueOf(productOrderEntity.getPayment().getTransactionId()),
            productOrderEntity.getTotalPrice(),
            "KRW",
            productOrderEntity.getOrderItems().stream().map(v -> new OrderStartPayload.OrderItem(
                String.valueOf(v.getProductId()),
                v.getQuantity(),
                v.getUnitPrice()
            )).toList()
        ));

        return productOrderEntity;
    }
}
