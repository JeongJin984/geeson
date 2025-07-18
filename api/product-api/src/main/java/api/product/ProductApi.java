package api.product;

import api.product.dto.ProductRegisterRequest;
import api.product.dto.ProductRegisterResponse;
import app.product.app.ProductRegisterApp;
import app.product.command.ProductRegisterCommand;
import domain.product.domain.entity.ProductJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductRegisterApp productRegisterApp;

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
}
