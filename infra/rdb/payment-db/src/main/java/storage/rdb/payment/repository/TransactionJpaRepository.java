package storage.rdb.payment.repository;

import app.payment.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.payment.repository.adapter.SpringDataTransactionJpaRepository;

@Repository
@RequiredArgsConstructor
public class TransactionJpaRepository implements TransactionRepository {
    private final SpringDataTransactionJpaRepository repository;
}