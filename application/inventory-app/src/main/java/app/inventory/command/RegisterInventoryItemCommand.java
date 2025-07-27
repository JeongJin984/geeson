package app.inventory.command;

public record RegisterInventoryItemCommand(
    Long inventoryId,
    Long batchLotId,
    String serialNumber,
    String status
) {}
