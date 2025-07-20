package app.payment.app;

import app.payment.command.PaymentMethodRegisterCommand;
import domain.payment.entity.PaymentMethodJpaEntity;
import domain.payment.repository.PaymentMethodRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import support.constants.payment.PgProviderCode;
import support.masking.CardMasker;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentMethodRegisterApp {
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodJpaEntity register(PaymentMethodRegisterCommand command) {
        return paymentMethodRepository.save(
            PaymentMethodJpaEntity.builder()
                .customerId(command.customerId())
                .type(command.type())
                .cardCode(command.cardCode())
                .provider(PgProviderCode.valueOf(command.provider()))
                .maskedNumber(CardMasker.mask(command.cardNumber()))
                .expirationDate(command.expirationDate())
                .billingKey(command.billingKey())
                .createdAt(LocalDateTime.now())
                .build()
        );
    }
}
