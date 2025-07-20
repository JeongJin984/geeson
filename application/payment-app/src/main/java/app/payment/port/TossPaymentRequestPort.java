package app.payment.port;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.math.BigDecimal;

public interface TossPaymentRequestPort {
    JSONObject tossPaymentRequest(String paymentKey, String orderId, BigDecimal amount) throws IOException, ParseException;
    JSONObject tossBillingPaymentRequest(String billingKey) throws IOException, ParseException;
}
