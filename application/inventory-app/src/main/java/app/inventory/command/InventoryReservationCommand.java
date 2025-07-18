package app.inventory.command;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InventoryReservationCommand(
    @NotNull Long reservationId,
    @NotNull Long productId,
    @NotNull Long orderId,
    @Min(1) int reservedQuantity
) {}