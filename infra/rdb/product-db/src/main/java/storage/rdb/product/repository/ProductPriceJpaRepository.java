package storage.rdb.product.repository;

import app.product.repository.ProductPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataProductPriceJpaRepository;

@Repository
@RequiredArgsConstructor
public class ProductPriceJpaRepository implements ProductPriceRepository {
    private final SpringDataProductPriceJpaRepository repository;
}
