package support.enums;

public enum ProductStatus {
    SELLING("0000", "판매 중"),
    SOLD_OUT("X000", "판매 금지"),
    INACTIVE("X999", "판매 중지")
    ;

    private final String code;
    private final String desc;

    ProductStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
