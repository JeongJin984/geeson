package support.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import support.messaging.saga.BaseCommand;

@Getter
@AllArgsConstructor
public class Command<T> {
    private final T payload;
    @Setter
    private BaseCommand baseCommand;

    public Command(T payload) {
        this.payload = payload;
        this.baseCommand = null;
    }
}
