package app.backoffice.repository;

import app.backoffice.dto.ProductDto;
import org.springframework.stereotype.Repository;
import support.enums.ProductStatus;

import java.util.List;

@Repository
public interface ProductAdminRepository {
    Long countProduct(Boolean active, List<ProductStatus> status);
    List<ProductDto> getLowStockProducts(Boolean isActive, List<ProductStatus> status, Integer page, Integer pageSize);
}
