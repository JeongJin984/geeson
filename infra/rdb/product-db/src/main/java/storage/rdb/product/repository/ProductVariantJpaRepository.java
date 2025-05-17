package storage.rdb.product.repository;

import app.product.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataProductVariantJpaRepository;

@Repository
@RequiredArgsConstructor
public class ProductVariantJpaRepository implements ProductVariantRepository {
    private final SpringDataProductVariantJpaRepository repository;
}
