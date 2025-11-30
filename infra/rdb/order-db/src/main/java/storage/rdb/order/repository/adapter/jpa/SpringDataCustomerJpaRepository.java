package storage.rdb.order.repository.adapter.jpa;

import domain.order.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCustomerJpaRepository extends JpaRepository<CustomerJpaEntity, Long> {
}
