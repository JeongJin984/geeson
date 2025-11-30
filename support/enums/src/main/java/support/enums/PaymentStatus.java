package support.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum PaymentStatus {
    SUCCESS("0000", "성공"),
    INVALID_CARD_NUMBER("X019", "잘못된 카드번호"),
    FAILURE("X999", "알수없는 오류");

    private final String code;
    private final String desc;

    PaymentStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static List<PaymentStatus> getErrorStatus() {
        return Arrays.stream(PaymentStatus.values()).filter(v -> !v.getCode().startsWith("0")).toList();
    }
}
