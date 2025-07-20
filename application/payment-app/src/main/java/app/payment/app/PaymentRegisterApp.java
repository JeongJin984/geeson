package app.payment.app;

import domain.payment.entity.PaymentJpaEntity;
import domain.payment.entity.PaymentMethodJpaEntity;
import domain.payment.entity.TransactionJpaEntity;
import domain.payment.repository.PaymentGatewayRepository;
import domain.payment.repository.PaymentMethodRepository;
import domain.payment.repository.PaymentRepository;
import domain.payment.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import support.constants.payment.PaymentStatus;
import support.constants.payment.PgProviderCode;
import support.uuid.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PaymentRegisterApp {
    private final PaymentRepository paymentRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final TransactionRepository transactionRepository;
    private final PaymentGatewayRepository paymentGatewayRepository;
    private final UuidGenerator uuidGenerator;
    private final JSONParser jsonParser = new JSONParser();

    public PaymentJpaEntity registerTossPayment(TransactionJpaEntity transaction) throws ParseException {
        JSONObject paymentResponse = (JSONObject) jsonParser.parse(transaction.getResultMessage());

        Optional<PaymentJpaEntity> orderPayment = paymentRepository.findByOrderId(Long.valueOf(paymentResponse.get("orderId").toString()));

        if(orderPayment.isPresent()) {
            log.info("payment already registered {}", paymentResponse.get("orderId").toString());
            return orderPayment.get();
        }

        Optional<PaymentMethodJpaEntity> paymentMethod = paymentMethodRepository.findByOrderId(paymentResponse.get("orderId").toString());

        if(paymentMethod.isPresent()) {
            paymentMethod.get().setPaymentResult(
                paymentResponse.get("orderId").toString(),
                "EASY_PAY",
                ((JSONObject)paymentResponse.get("card")).get("issuerCode").toString(),
                PgProviderCode.TOSS_PAY,
                ((JSONObject)paymentResponse.get("card")).get("number").toString()
            );
        } else {
            paymentMethod = Optional.of(
                paymentMethodRepository.save(new PaymentMethodJpaEntity(
                    uuidGenerator.nextId(),
                    null,
                    paymentResponse.get("orderId").toString(),
                    "EASY_PAY",
                    ((JSONObject)paymentResponse.get("card")).get("issuerCode").toString(),
                    PgProviderCode.TOSS_PAY,
                    ((JSONObject)paymentResponse.get("card")).get("number").toString(),
                    null,
                    null,
                    LocalDateTime.now()
                ))
            );
        }

        PaymentJpaEntity payment = paymentRepository.save(
            PaymentJpaEntity.builder()
                .paymentId(uuidGenerator.nextId())
                .orderId(paymentResponse.get("orderId").toString())
                .amount(new BigDecimal(((JSONObject)paymentResponse.get("card")).get("number").toString()))
                .currency("KRW")
                .status(PaymentStatus.SUCCEED)
                .paymentMethod(paymentMethod.get())
                .requestedAt(LocalDateTime.now())
                .build()
        );


        return payment;
    }
    
    /**
     * Get all payments for a specific customer
     * @param customerId the ID of the customer
     * @return list of payments for the customer
     */
    public List<PaymentJpaEntity> getCustomerPayments(Long customerId) {
        return paymentRepository.findByCustomerId(customerId);
    }
}
