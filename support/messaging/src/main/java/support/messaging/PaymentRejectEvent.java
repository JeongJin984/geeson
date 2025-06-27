package support.messaging;

public record PaymentRejectEvent(
    String paymentId,
    String orderId,
    String message
) {
}
