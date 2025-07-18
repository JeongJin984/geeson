package api.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegisterResponse {
    private Long productId;
    private String name;
    private String sku;
    private String brand;
    private Boolean isActive;
    private String message;
}