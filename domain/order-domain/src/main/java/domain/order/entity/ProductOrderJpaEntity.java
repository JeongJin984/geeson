package domain.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
public class ProductOrderJpaEntity {
    @Id
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerJpaEntity customer;

    private BigDecimal totalPrice;
    private String status;
    private LocalDateTime orderDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipping_address_id")
    private ShippingAddressJpaEntity shippingAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private PaymentRequestJpaEntity payment;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemJpaEntity> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItemJpaEntity item) {
        if(this.orderItems == null) this.orderItems = new ArrayList<>();
        this.orderItems.add(item);
    }

    public void registerPayment(PaymentRequestJpaEntity payment) {
        this.payment = payment;
        payment.registerOrder(this);
    }

    public void setOrderItem(List<OrderItemJpaEntity> orderItems) {
        if(this.orderItems == null) this.orderItems = new ArrayList<>();
        this.orderItems.addAll(orderItems);
        orderItems.forEach(v -> v.setOrder(this));
    }

    public ProductOrderJpaEntity(Long orderId, CustomerJpaEntity customer, BigDecimal totalPrice, String status, ShippingAddressJpaEntity shippingAddress) {
        this.orderId = orderId;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDate = LocalDateTime.now();
        this.shippingAddress = shippingAddress;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
