package support.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import support.messaging.saga.BaseEvent;

@Getter
@AllArgsConstructor
public class Event<T> {
    private final T payload;
    @Setter
    private BaseEvent baseEvent;

    public Event(T payload) {
        this.payload = payload;
        this.baseEvent = null;
    }
}
