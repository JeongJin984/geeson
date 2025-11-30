package storage.rdb.product.repository.adapter.jpa;

import domain.product.domain.entity.ProductVisibilityJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductVisibilityJpaRepository extends JpaRepository<ProductVisibilityJpaEntity, Long> {
}
