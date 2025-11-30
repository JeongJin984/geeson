package api.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "api.backoffice",
    "app.backoffice"
})
public class GeesonBackofficeApiMain {
    public static void main(String[] args) {
        SpringApplication.run(GeesonBackofficeApiMain.class, args);
    }
}
