package storage.rdb.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {"storage.rdb.payment", "domain.payment", "app.backoffice"}
)
public class PaymentDbMain {
    public static void main(String[] args) { SpringApplication.run(PaymentDbMain.class, args); }
}
