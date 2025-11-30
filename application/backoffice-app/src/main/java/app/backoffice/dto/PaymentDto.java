package app.backoffice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDto (
    String paymentId,
    String orderId,
    String paymentStatus,
    BigDecimal amount,
    LocalDateTime reqDt
) {
}
