package storage.rdb.payment.repository.adapter;

import domain.payment.domain.entity.PaymentStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPaymentStatusHistoryJpaRepository extends JpaRepository<PaymentStatusHistory, Long> {
}
