package storage.rdb.product.repository;

import app.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataProductJpaRepository;

@Repository
@RequiredArgsConstructor
public class ProductJpaRepository implements ProductRepository {
    private final SpringDataProductJpaRepository repository;
}