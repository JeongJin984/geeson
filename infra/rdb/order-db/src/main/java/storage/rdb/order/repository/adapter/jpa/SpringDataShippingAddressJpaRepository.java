package storage.rdb.order.repository.adapter.jpa;

import domain.order.entity.ShippingAddressJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataShippingAddressJpaRepository extends JpaRepository<ShippingAddressJpaEntity, Long> {
}