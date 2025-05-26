package domain.product.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_visibility")
public class ProductVisibilityJpaEntity {
    @Id
    private Long productId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "product_id")
    private ProductJpaEntity product;

    private Boolean isVisible;
    private LocalDateTime visibilityStart;
    private LocalDateTime visibilityEnd;
}
