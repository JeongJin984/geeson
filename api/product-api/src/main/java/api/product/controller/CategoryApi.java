package api.product.controller;

import api.product.dto.CategoryRegisterRequest;
import api.product.dto.CategoryRegisterResponse;
import app.product.app.CategoryRegisterApp;
import app.product.command.CategoryRegisterCommand;
import domain.product.domain.entity.ProductCategoryJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryApi {

    private final CategoryRegisterApp categoryRegisterApp;

    @PostMapping
    public ResponseEntity<CategoryRegisterResponse> registerCategory(@RequestBody CategoryRegisterRequest request) {
        // Convert request to command
        CategoryRegisterCommand command = CategoryRegisterCommand.builder()
                .name(request.getName())
                .description(request.getDescription())
                .parentId(request.getParentId())
                .build();

        // Register category
        ProductCategoryJpaEntity category = categoryRegisterApp.registerCategory(command);

        // Return response
        return ResponseEntity.ok(new CategoryRegisterResponse(
                category.getCategoryId(),
                category.getName(),
                category.getDescription(),
                category.getParentId(),
                category.getIsActive(),
                "category registered successfully"
        ));
    }
}