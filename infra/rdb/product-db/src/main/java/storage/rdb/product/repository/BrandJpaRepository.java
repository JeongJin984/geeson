package storage.rdb.product.repository;

import app.product.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataBrandJpaRepository;

@Repository
@RequiredArgsConstructor
public class BrandJpaRepository implements BrandRepository {
    private final SpringDataBrandJpaRepository repository;
}
