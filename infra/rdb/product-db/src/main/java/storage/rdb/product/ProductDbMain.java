package storage.rdb.product;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication(
    scanBasePackages = {"storage.rdb.product", "domain.product", "app.product"}
)
public class ProductDbMain {
    public static void main(String[] args) { run(ProductDbMain.class, args); }
}