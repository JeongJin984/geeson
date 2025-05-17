package storage.rdb.product.repository;

import app.product.repository.ProductQnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataProductQnAJpaRepository;

@Repository
@RequiredArgsConstructor
public class ProductQnAJpaRepository implements ProductQnARepository {
    private final SpringDataProductQnAJpaRepository repository;
}
