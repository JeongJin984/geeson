package domain.product.domain.repository;

import domain.product.domain.entity.ProductCategoryJpaEntity;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository {
    ProductCategoryJpaEntity save(ProductCategoryJpaEntity category);
    Optional<ProductCategoryJpaEntity> findById(Long categoryId);
    List<ProductCategoryJpaEntity> findAll();
    Optional<ProductCategoryJpaEntity> findByName(String name);
}