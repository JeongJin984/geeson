package support.enums;

public enum RefundStatus {
    SUCCESS("0000", "성공"),
    REQUEST("R000", "환불 요청"),
    EXPIRED("X999", "환불 가능 기간 초과"),
    FAILURE("X999", "알수없는 오류");

    private final String code;
    private final String desc;

    RefundStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
