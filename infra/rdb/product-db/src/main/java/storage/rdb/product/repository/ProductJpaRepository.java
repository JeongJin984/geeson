package storage.rdb.product.repository;

import app.backoffice.dto.ProductDto;
import app.backoffice.repository.ProductAdminRepository;
import domain.product.domain.entity.ProductJpaEntity;
import domain.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import storage.rdb.product.repository.adapter.jpa.SpringDataProductJpaRepository;
import storage.rdb.product.repository.adapter.mybatis.ProductMybatisRepository;
import support.enums.ProductStatus;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductJpaRepository implements ProductRepository, ProductAdminRepository {
    private final SpringDataProductJpaRepository repository;
    private final ProductMybatisRepository mybatisRepository;

    @Override
    public ProductJpaEntity findBySku(String sku) {
        return repository.findBySku(sku);
    }

    @Override
    public ProductJpaEntity save(ProductJpaEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<ProductJpaEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Long countProduct(Boolean active, List<ProductStatus> status) {
        return mybatisRepository.countProduct(active, status);
    }

    @Override
    public List<ProductDto> getLowStockProducts(Boolean isActive, List<ProductStatus> status, Integer page, Integer pageSize) {
        return mybatisRepository.getLowStockProducts(isActive, status, page, pageSize);
    }
}