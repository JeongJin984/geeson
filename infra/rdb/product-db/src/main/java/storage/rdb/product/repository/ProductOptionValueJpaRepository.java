package storage.rdb.product.repository;

import app.product.repository.ProductOptionValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataProductOptionValueJpaRepository;

@Repository
@RequiredArgsConstructor
public class ProductOptionValueJpaRepository implements ProductOptionValueRepository {
    private final SpringDataProductOptionValueJpaRepository repository;
}