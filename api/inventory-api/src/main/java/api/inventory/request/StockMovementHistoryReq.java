package api.inventory.request;

import module.enums.MovementType;

public record StockMovementHistoryReq(
    Long inventoryId,
    MovementType movementType
) {}