package storage.rdb.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {"storage.rdb.order", "domain.order"}
)
public class OrderDbMain {
}
