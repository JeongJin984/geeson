package support.messaging;

import java.math.BigDecimal;

public record PaymentRequestPayload(
    String customerId,
    String orderId,
    String paymentMethodId,
    String transactionId,
    BigDecimal totalPrice,
    String currency
) {

}
