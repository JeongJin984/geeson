package api.product;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApi {

    @GetMapping("/awefacewsd")
    public String foo() {
        return "foo";
    }
}
