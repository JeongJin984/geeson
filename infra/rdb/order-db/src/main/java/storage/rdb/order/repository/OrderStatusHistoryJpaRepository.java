package storage.rdb.order.repository;

import domain.order.repository.OrderStatusHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import storage.rdb.order.repository.adapter.jpa.SpringDataOrderStatusHistoryJpaRepository;

@Repository
@RequiredArgsConstructor
public class OrderStatusHistoryJpaRepository implements OrderStatusHistoryRepository {
    private final SpringDataOrderStatusHistoryJpaRepository repository;
}
