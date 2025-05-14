package rdb.storage.order.core.repository;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemJpaRepository, Long> {
}