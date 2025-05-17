package storage.rdb.payment.repository;

import app.payment.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.payment.repository.adapter.SpringDataPaymentMethodJpaRepository;

@Repository
@RequiredArgsConstructor
public class PaymentMethodJpaRepository implements PaymentMethodRepository {
    private final SpringDataPaymentMethodJpaRepository repository;
}

