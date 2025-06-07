package domain.payment.repository;

import domain.payment.entity.PaymentMethodJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentMethodRepository {
    PaymentMethodJpaEntity save(PaymentMethodJpaEntity entity);
    Optional<PaymentMethodJpaEntity> findByPaymentMethodId(Long paymentMethodId);
    List<PaymentMethodJpaEntity> findAllByCustomerId(Long customerId);
}

