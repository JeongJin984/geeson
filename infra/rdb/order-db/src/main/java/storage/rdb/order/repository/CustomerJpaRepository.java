package storage.rdb.order.repository;

import app.order.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.order.repository.adapter.SpringDataCustomerJpaRepository;

@Repository
@RequiredArgsConstructor
public class CustomerJpaRepository implements CustomerRepository {
    private final SpringDataCustomerJpaRepository repository;
}
