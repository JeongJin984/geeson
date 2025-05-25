package storage.rdb.payment.repository;

import app.payment.repository.PaymentRepository;
import domain.payment.domain.entity.PaymentJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import storage.rdb.payment.repository.adapter.SpringDataPaymentJpaRepository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentJpaRepository implements PaymentRepository {
    private final SpringDataPaymentJpaRepository repository;

    @Override
    public PaymentJpaEntity save(PaymentJpaEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<PaymentJpaEntity> findByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }
}