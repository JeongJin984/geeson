package storage.rdb.order.repository.adapter.jpa;

import domain.order.entity.OrderReturnJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataOrderReturnJpaRepository extends JpaRepository<OrderReturnJpaEntity, Long> {
}