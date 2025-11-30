package app.backoffice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record RefundDto (
    String refundId,
    String paymentId,
    BigDecimal refundAMount,
    String refundStatus,
    LocalDateTime reqDt
) {
}
