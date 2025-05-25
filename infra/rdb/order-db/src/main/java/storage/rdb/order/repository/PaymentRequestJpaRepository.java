package storage.rdb.order.repository;

import domain.order.domain.entity.PaymentRequestJpaEntity;
import domain.order.domain.repository.PaymentRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import storage.rdb.order.repository.adapter.SpringDataPaymentRequestJpaRepository;

@Repository
@RequiredArgsConstructor
public class PaymentRequestJpaRepository implements PaymentRequestRepository {
    private final SpringDataPaymentRequestJpaRepository repository;

    @Override
    public PaymentRequestJpaEntity save(PaymentRequestJpaEntity entity) {
        return repository.save(entity);
    }
}