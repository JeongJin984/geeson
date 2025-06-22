package support.messaging;

public record PaymentCompleteEvent(
    String paymentId,
    String orderId
) {
}
