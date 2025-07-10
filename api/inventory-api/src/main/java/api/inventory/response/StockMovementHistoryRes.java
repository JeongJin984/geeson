package api.inventory.response;

import domain.inventory.domain.entity.StockMovementJpaEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StockMovementHistoryRes {

    private Long movementId;
    private Long inventoryId;
    private String movementType;
    private Integer quantity;
    private LocalDateTime movementDate;
    private String description;
    private String referenceId;

    public static StockMovementHistoryRes from(StockMovementJpaEntity entity) {
        return StockMovementHistoryRes.builder()
                .movementId(entity.getMovementId())
                .inventoryId(entity.getInventory().getInventoryId())
                .movementType(entity.getMovementType().name())
                .quantity(entity.getQuantity())
                .movementDate(entity.getMovementDate())
                .description(entity.getDescription())
                .referenceId(entity.getReferenceId())
                .build();
    }
}