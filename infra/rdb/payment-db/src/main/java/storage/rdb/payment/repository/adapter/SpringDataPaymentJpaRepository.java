package storage.rdb.payment.repository.adapter;

import domain.payment.domain.entity.PaymentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPaymentJpaRepository extends JpaRepository<PaymentJpaEntity, Long> {
}