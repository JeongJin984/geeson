package storage.rdb.product.repository.adapter;

import domain.product.domain.entity.ProductCategoryMapJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductCategoryMapJpaRepository extends JpaRepository<ProductCategoryMapJpaEntity, Long> {
}