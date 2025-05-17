package storage.rdb.product.repository;

import app.product.repository.ProductDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataProductDetailJpaRepository;

@Repository
@RequiredArgsConstructor
public class ProductDetailJpaRepository implements ProductDetailRepository {
    private final SpringDataProductDetailJpaRepository repository;
}
