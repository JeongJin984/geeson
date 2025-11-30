package app.backoffice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductDto(
    String productId,
    String productName,
    String sku,
    Long stockAMount
) {
}
