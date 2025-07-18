package api.product.dto;

import java.math.BigDecimal;

public record ProductSelectResponse(
    Long productId,
    String name,
    String sku,
    String brandId,
    String brandName,
    Boolean isActive,
    BigDecimal price,
    BigDecimal discountPrice,
    String currency,
    String message
) {}