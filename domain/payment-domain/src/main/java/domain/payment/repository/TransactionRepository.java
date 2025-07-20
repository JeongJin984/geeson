package domain.payment.repository;

import domain.payment.entity.TransactionJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository {
    TransactionJpaEntity save(TransactionJpaEntity entity);
    Optional<TransactionJpaEntity> findByOrderId(String orderId);
}