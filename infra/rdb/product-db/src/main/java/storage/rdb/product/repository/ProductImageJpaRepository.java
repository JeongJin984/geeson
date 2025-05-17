package storage.rdb.product.repository;

import app.product.repository.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataProductImageJpaRepository;

@Repository
@RequiredArgsConstructor
public class ProductImageJpaRepository implements ProductImageRepository {
    private final SpringDataProductImageJpaRepository repository;
}