package domain.product.domain.repository;

import domain.product.domain.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, Long> {
    ProductJpaEntity findBySku(String sku);
}