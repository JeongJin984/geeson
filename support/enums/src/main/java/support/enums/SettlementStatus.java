package support.enums;

public enum SettlementStatus {
    COMPLETE("0000", "성공"),
    PENDING("S000", "정산 대기"),
    FAILURE("X999", "알수없는 오류");

    private final String code;
    private final String desc;

    SettlementStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
