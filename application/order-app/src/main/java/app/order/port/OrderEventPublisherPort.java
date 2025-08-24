package app.order.port;

import org.springframework.stereotype.Component;
import support.messaging.command.OrderStartPayload;

@Component
public interface OrderEventPublisherPort {
    void publishOrderCreated(OrderStartPayload event);
}
