package api.order;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(scanBasePackages = {"api.order", "redis.lock"})
public class OrderApiApplication {
    public static void main(String[] args) {
        run(OrderApiApplication.class, args);
    }
}