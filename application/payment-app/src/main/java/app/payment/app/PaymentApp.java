package app.payment.app;

import app.payment.command.PaymentConfirmCommand;
import domain.payment.domain.entity.PaymentJpaEntity;
import domain.payment.domain.entity.PaymentMethodJpaEntity;
import domain.payment.domain.entity.TransactionJpaEntity;
import domain.payment.domain.repository.PaymentMethodRepository;
import domain.payment.domain.repository.PaymentRepository;
import domain.payment.domain.repository.TransactionRepository;
import app.payment.exception.PaymentMethodNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentApp {
    private final PaymentRepository paymentRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final TransactionRepository transactionRepository;

    public PaymentJpaEntity registerPayment(PaymentConfirmCommand command) {
        PaymentMethodJpaEntity paymentMethod = paymentMethodRepository.findByPaymentMethodId(command.paymentMethodId())
            .orElseThrow(() -> new PaymentMethodNotFoundException("payment method not found"));

        PaymentJpaEntity payment = paymentRepository.save(
            PaymentJpaEntity.builder()
                .orderId(command.orderId())
                .amount(command.amount())
                .currency(command.currency())
                .status(command.status())
                .paymentMethod(paymentMethod)
                .requestedAt(LocalDateTime.now())
                .build()
        );

        transactionRepository.save(
            TransactionJpaEntity.builder()
                .transactionId(command.transactionId())
                .payment(payment)
                .transactionType("PAYMENT")
                .amount(payment.getAmount())
                .pgTransactionId(command.pgConfirmInfo().paymentKey())
                .resultCode(payment.getStatus())
                .resultMessage(command.pgRes())
                .build()
        );

        return payment;
    }
}
