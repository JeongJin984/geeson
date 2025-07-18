package api.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRegisterRequest {
    private String name;
    private String sku;
    private Long brandId;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private String currency;
}