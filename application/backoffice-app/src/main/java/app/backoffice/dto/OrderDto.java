package app.backoffice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderDto(
    String orderId,
    String customerId,
    String orderStatus,
    LocalDateTime reqDt,
    BigDecimal amount
) {
}
