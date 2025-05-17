package storage.rdb.product.repository;

import app.product.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataProductOptionJpaRepository;

@Repository
@RequiredArgsConstructor
public class ProductOptionJpaRepository implements ProductOptionRepository {
    private final SpringDataProductOptionJpaRepository repository;
}