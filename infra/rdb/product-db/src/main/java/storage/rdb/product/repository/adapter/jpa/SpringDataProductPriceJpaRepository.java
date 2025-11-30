package storage.rdb.product.repository.adapter.jpa;

import domain.product.domain.entity.ProductPriceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductPriceJpaRepository extends JpaRepository<ProductPriceJpaEntity, Long> {
}
