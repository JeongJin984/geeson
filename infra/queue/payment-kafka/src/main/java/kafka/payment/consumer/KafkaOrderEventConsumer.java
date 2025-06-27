package kafka.payment.consumer;

import app.payment.app.PaymentApp;
import app.payment.command.PaymentConfirmCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.payment.entity.PaymentJpaEntity;
import kafka.payment.PGConfirmRes;
import kafka.payment.TossInfraRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import support.messaging.*;
import support.messaging.saga.BaseEvent;
import support.uuid.UuidGenerator;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaOrderEventConsumer {
    private final PaymentApp paymentApp;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TossInfraRequest tossInfraRequest;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final UuidGenerator uuidGenerator;

    @KafkaListener(topics = "payment-request-command", groupId = "order-consumer-group")
    public void paymentRequestCommand(Command<PaymentRequestPayload> event) throws JsonProcessingException {
        BaseEvent.BaseEventBuilder baseEventBuilder = BaseEvent.builder()
            .eventId(String.valueOf(uuidGenerator.nextId()))
            .aggregateType("payment")
            .source("payment-api")
            .sagaId(event.getBaseCommand().getSagaId())
            .stepId(event.getBaseCommand().getStepId())
            .message("complete");

        try {
            PGConfirmRes res = tossInfraRequest.pgConfirmRequest();

            PaymentJpaEntity paymentJpaEntity = paymentApp.registerPayment(new PaymentConfirmCommand(
                Long.parseLong(event.getPayload().customerId()),
                Long.parseLong(event.getPayload().orderId()),
                event.getPayload().transactionId(),
                event.getPayload().totalPrice(),
                event.getPayload().currency(),
                res.status(),
                Long.parseLong(event.getPayload().paymentMethodId()),
                new PaymentConfirmCommand.PGConfirmInfo(
                    "TOSS",
                    "test_" + UUID.randomUUID(),
                    "TOSS00001",
                    res.paymentKey(),
                    res.orderId(),
                    res.totalAmount()
                ),
                res.toString()
            ));

            Event<PaymentCompleteEvent> completeEvent = new Event<>(
                new PaymentCompleteEvent(
                    String.valueOf(paymentJpaEntity.getPaymentId()),
                    String.valueOf(paymentJpaEntity.getOrderId())
                ),
                baseEventBuilder
                    .aggregateId(String.valueOf(paymentJpaEntity.getPaymentId()))
                    .build()
            );

            kafkaTemplate.send("payment-complete-event", objectMapper.writeValueAsString(completeEvent));
        } catch (IOException e) {
            Event<PaymentRejectEvent> rejectEvent = new Event<>(
                new PaymentRejectEvent(
                    "-1",
                    String.valueOf(event.getPayload().orderId()),
                    e.getMessage()
                ),
                baseEventBuilder.build()
            );

            kafkaTemplate.send("payment-reject-event", objectMapper.writeValueAsString(rejectEvent));
            throw new RuntimeException(e);
        }
    }
}
