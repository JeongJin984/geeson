package storage.rdb.order.repository.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import storage.rdb.order.core.entity.ShippingAddressJpaEntity;

public interface SpringDataShippingAddressJpaRepository extends JpaRepository<ShippingAddressJpaEntity, Long> {
}