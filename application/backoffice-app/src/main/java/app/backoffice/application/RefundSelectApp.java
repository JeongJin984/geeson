package app.backoffice.application;

import app.backoffice.dto.RefundDto;
import app.backoffice.repository.RefundAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import support.enums.RefundStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundSelectApp {
    private final RefundAdminRepository refundRepository;

    public Long countRefund(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<RefundStatus> refundStatus) {
        return refundRepository.countRefund(reqDtSt, reqDtEd, refundStatus);
    }

    public List<RefundDto> getRefunds(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<RefundStatus> refundStatus, Integer page, Integer pageSize) {
        return refundRepository.getRefunds(reqDtSt, reqDtEd, refundStatus, page, pageSize);
    }
}
