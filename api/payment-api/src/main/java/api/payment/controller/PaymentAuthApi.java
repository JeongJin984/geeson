package api.payment.controller;

import app.payment.app.PaymentConfirmApp;
import app.payment.app.PaymentRegisterApp;
import domain.payment.entity.PaymentJpaEntity;
import domain.payment.entity.TransactionJpaEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/payment-auth/")
@Slf4j
@RequiredArgsConstructor
public class PaymentAuthApi {
    private final PaymentConfirmApp paymentConfirmApp;
    private final PaymentRegisterApp paymentRegisterApp;

    @GetMapping("/toss/success")
    public ResponseEntity<String> tossPaymentAuthorizationSuccess(
        @RequestParam String paymentType,
        @RequestParam String orderId,
        @RequestParam String paymentKey,
        @RequestParam String amount
    ) throws ParseException, IOException {
        if(paymentType.equals("NORMAL")) {
            TransactionJpaEntity transaction = paymentConfirmApp.tossPaymentRequest(
                paymentKey, orderId, new BigDecimal(amount)
            );

            PaymentJpaEntity payment = paymentRegisterApp.registerTossPayment(transaction);
            return ResponseEntity.ok().body(payment.toString());
        } else {
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Supported");
        }
    }
}
