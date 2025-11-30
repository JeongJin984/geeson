package storage.rdb.product.repository.adapter.mybatis;

import app.backoffice.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;
import support.enums.ProductStatus;

import java.util.List;

@Mapper
public interface ProductMybatisRepository {
    Long countProduct(Boolean active, List<ProductStatus> status);

    List<ProductDto> getLowStockProducts(Boolean isActive, List<ProductStatus> status, Integer page, Integer pageSize);
}
