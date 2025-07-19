package api.product.controller;

import api.product.dto.ProductRegisterRequest;
import api.product.dto.ProductRegisterResponse;
import api.product.dto.ProductSelectResponse;
import app.product.app.ProductRegisterApp;
import app.product.app.ProductSelectApp;
import app.product.command.ProductRegisterCommand;
import domain.product.domain.entity.ProductJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductRegisterApp productRegisterApp;
    private final ProductSelectApp productSelectApp;

    @PostMapping
    public ResponseEntity<ProductRegisterResponse> registerProduct(@RequestBody ProductRegisterRequest request) {
        // Convert request to command
        ProductRegisterCommand command = ProductRegisterCommand.builder()
                .name(request.getName())
                .sku(request.getSku())
                .brandId(request.getBrandId())
                .price(request.getPrice())
                .discountPrice(request.getDiscountPrice())
                .currency(request.getCurrency())
                .build();

        // Register product
        ProductJpaEntity product = productRegisterApp.registerProduct(command);

        // Return response
        return ResponseEntity.ok(new ProductRegisterResponse(
            product.getProductId(),
            product.getName(),
            product.getSku(),
            product.getBrand().getBrandId().toString(),
            product.getIsActive(),
            "product registered successfully"
        ));
    }
    
    @GetMapping("/{productId}")
    public ResponseEntity<ProductSelectResponse> selectProduct(@PathVariable Long productId) {
        // Select product
        ProductSelectApp.ProductWithPrice productWithPrice = productSelectApp.selectProduct(productId);
        
        // Extract product and price information
        ProductJpaEntity product = productWithPrice.product();
        var price = productWithPrice.price();
        
        // Return response
        return ResponseEntity.ok(new ProductSelectResponse(
            product.getProductId(),
            product.getName(),
            product.getSku(),
            product.getBrand().getBrandId().toString(),
            product.getBrand().getName(),
            product.getIsActive(),
            price.getPrice(),
            price.getDiscountPrice(),
            price.getCurrency(),
            "product retrieved successfully"
        ));
    }
}
