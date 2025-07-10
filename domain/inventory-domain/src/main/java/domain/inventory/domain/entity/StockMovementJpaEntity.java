package domain.inventory.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import module.enums.MovementType;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "stock_movements")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockMovementJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id")
    private Long movementId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private InventoryJpaEntity inventory;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type", nullable = false)
    private MovementType movementType;

    @Column(nullable = false)
    private Integer quantity;

    private String description;

    @Column(name = "movement_date", nullable = false)
    private LocalDateTime movementDate;

    @Column(name = "reference_id")
    private String referenceId;

    @PrePersist
    protected void onCreate() {
        this.movementDate = LocalDateTime.now();
    }

    public static StockMovementJpaEntity create(
            Long inventoryId,
            MovementType movementType,
            Integer quantity,
            String description,
            String referenceId) {
        StockMovementJpaEntity entity = new StockMovementJpaEntity();
        entity.inventory = InventoryJpaEntity.withId(inventoryId);
        entity.movementType = movementType;
        entity.quantity = quantity;
        entity.description = description;
        entity.referenceId = referenceId;
        return entity;
    }

    public void markAsStockIn() {
        this.movementType = MovementType.IN;
    }

    public void markAsStockOut() {
        this.movementType = MovementType.OUT;
    }
}
