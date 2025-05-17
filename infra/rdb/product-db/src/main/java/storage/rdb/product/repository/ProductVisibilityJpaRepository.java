package storage.rdb.product.repository;

import app.product.repository.ProductVisibilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataProductVisibilityJpaRepository;

@Repository
@RequiredArgsConstructor
public class ProductVisibilityJpaRepository implements ProductVisibilityRepository {
    private final SpringDataProductVisibilityJpaRepository repository;
}
