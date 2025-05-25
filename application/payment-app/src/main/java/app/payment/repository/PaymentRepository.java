package app.payment.repository;

import domain.payment.domain.entity.PaymentJpaEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository  {
    PaymentJpaEntity save(PaymentJpaEntity entity);
}