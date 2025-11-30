package app.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "app.backoffice"
})
public class BackOfficeAppMain {
    public static void main(String[] args) { SpringApplication.run(BackOfficeAppMain.class, args); }
}
