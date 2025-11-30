package storage.rdb.order.repository.adapter.jpa;

import domain.order.entity.OrderItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataOrderItemJpaRepository extends JpaRepository<OrderItemJpaEntity, Long> {
}