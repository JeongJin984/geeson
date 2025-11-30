package app.backoffice.application;

import app.backoffice.dto.ProductDto;
import app.backoffice.repository.ProductAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import support.enums.ProductStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSelectApp {
    private final ProductAdminRepository productAdminRepository;

    public Long countProduct(Boolean isActive, List<ProductStatus> productStatus) {
        return productAdminRepository.countProduct(isActive, productStatus);
    }

    public List<ProductDto> getLowStockProducts(Boolean isActive, List<ProductStatus> productStatus, Integer page, Integer pageSize) {
        return productAdminRepository.getLowStockProducts(isActive, productStatus, page, pageSize);
    }
}
