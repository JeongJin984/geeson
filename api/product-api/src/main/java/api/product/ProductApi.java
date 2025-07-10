package api.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApi {

    @GetMapping("/asdfawec")
    public String asdfawec() {
        return "asdfawec";
    }
}
