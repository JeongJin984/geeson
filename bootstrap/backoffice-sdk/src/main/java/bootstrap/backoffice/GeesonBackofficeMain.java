package bootstrap.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "bootstrap.backoffice",
    "api.backoffice"
})
public class GeesonBackofficeMain {
    public static void main(String[] args) {
        SpringApplication.run(GeesonBackofficeMain.class, args);
    }
}
