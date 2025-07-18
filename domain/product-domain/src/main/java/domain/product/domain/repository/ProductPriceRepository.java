package domain.product.domain.repository;

import domain.product.domain.entity.ProductPriceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPriceJpaEntity, Long> {
}
