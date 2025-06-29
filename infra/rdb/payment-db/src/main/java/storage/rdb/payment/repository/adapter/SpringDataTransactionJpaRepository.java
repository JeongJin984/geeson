package storage.rdb.payment.repository.adapter;

import domain.payment.entity.TransactionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTransactionJpaRepository extends JpaRepository<TransactionJpaEntity, Long> {
}