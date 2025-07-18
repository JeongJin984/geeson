package domain.product.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
public class ProductJpaEntity {
    @Id
    private Long productId;

    private String name;
    private String sku;
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandJpaEntity brand;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public ProductJpaEntity(Long productId, String name, String sku, Boolean isActive, BrandJpaEntity brand) {
        this.productId = productId;
        this.name = name;
        this.sku = sku;
        this.isActive = isActive;
        this.brand = brand;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}