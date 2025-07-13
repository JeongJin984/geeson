package api.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"api.inventory", "storage.rdb.inventory"}
)
public class InventoryApi {
    public static void main(String[] args) {
        SpringApplication.run(InventoryApi.class, args);
    }
}
