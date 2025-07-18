package app.product.app;

import app.product.exception.NoSuchProductException;
import domain.product.domain.entity.ProductJpaEntity;
import domain.product.domain.entity.ProductPriceJpaEntity;
import domain.product.domain.repository.ProductPriceRepository;
import domain.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductSelectApp {
    
    private final ProductRepository productRepository;
    private final ProductPriceRepository productPriceRepository;
    
    @Transactional(readOnly = true)
    public ProductWithPrice selectProduct(Long productId) {
        // Find product by ID
        ProductJpaEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchProductException("Product with ID " + productId + " not found"));
        
        // Find product price by ID (same ID as product)
        ProductPriceJpaEntity productPrice = productPriceRepository.findById(productId)
                .orElseThrow(() -> new NoSuchProductException("Price information for product with ID " + productId + " not found"));
        
        // Return product with price information
        return new ProductWithPrice(product, productPrice);
    }
    
    // Inner class to hold product and price information
    public record ProductWithPrice(ProductJpaEntity product, ProductPriceJpaEntity price) {}
}