package storage.rdb.product.repository;

import domain.product.domain.repository.ProductQnARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.jpa.SpringDataProductQnAJpaRepository;

@Repository
@RequiredArgsConstructor
public class ProductQnAJpaRepository implements ProductQnARepository {
    private final SpringDataProductQnAJpaRepository repository;
}
