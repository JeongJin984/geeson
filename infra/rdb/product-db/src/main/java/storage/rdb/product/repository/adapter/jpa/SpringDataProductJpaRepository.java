package storage.rdb.product.repository.adapter.jpa;

import domain.product.domain.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {
    ProductJpaEntity findBySku(String sku);
}