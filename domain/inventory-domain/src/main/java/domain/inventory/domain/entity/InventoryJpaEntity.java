package domain.inventory.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InventoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private WarehouseJpaEntity warehouse;

    private Integer totalQuantity;
    private Integer reservedQuantity;

    private Integer reorderLevel;
    private Integer reorderQuantity;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    /**
     * 계산용 필드
     * @return Integer
     */
    @Transient
    public Integer getAvailableQuantity() {
        return totalQuantity - reservedQuantity;
    }
}