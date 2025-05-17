package storage.rdb.product.repository;

import app.product.repository.ProductReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.SpringDataProductReviewJpaRepository;

@Repository
@RequiredArgsConstructor
public class ProductReviewJpaRepository implements ProductReviewRepository {
    private final SpringDataProductReviewJpaRepository repository;
}