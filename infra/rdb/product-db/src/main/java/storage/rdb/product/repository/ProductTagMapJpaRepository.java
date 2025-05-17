package storage.rdb.product.repository;

import app.product.repository.ProductTagMapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataProductTagMapJpaRepository;

@Repository
@RequiredArgsConstructor
public class ProductTagMapJpaRepository implements ProductTagMapRepository {
    private final SpringDataProductTagMapJpaRepository repository;
}