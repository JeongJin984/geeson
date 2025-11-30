package storage.rdb.order.repository.adapter.jpa;

import domain.order.entity.OrderStatusHistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataOrderStatusHistoryJpaRepository extends JpaRepository<OrderStatusHistoryJpaEntity, Long> {
}
