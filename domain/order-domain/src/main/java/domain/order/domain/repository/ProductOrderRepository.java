package domain.order.domain.repository;

import domain.order.domain.entity.ProductOrderJpaEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository  {
    ProductOrderJpaEntity save(ProductOrderJpaEntity entity);
}
