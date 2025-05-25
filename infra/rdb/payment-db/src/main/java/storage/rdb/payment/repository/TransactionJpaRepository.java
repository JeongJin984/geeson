package storage.rdb.payment.repository;

import domain.payment.domain.entity.TransactionJpaEntity;
import domain.payment.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import storage.rdb.payment.repository.adapter.SpringDataTransactionJpaRepository;

@Repository
@RequiredArgsConstructor
public class TransactionJpaRepository implements TransactionRepository {
    private final SpringDataTransactionJpaRepository repository;

    @Override
    public TransactionJpaEntity save(TransactionJpaEntity entity) {
        return repository.save(entity);
    }
}