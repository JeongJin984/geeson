package storage.rdb.product.repository.adapter.jpa;

import domain.product.domain.entity.ProductDetailJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductDetailJpaRepository extends JpaRepository<ProductDetailJpaEntity, Long> {
}
