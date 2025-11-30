package support.enums;

public enum OrderStatus {
    COMPLETE("0000", "성공"),
    REQUESTED("R000", "요청"),
    PENDING("P000", "처리 대기중"),
    CANCELED("C000", "고객 취소"),
    REJECTED("X999", "거절")
    ;

    private final String code;
    private final String desc;

    OrderStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
