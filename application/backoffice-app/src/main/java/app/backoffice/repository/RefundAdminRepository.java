package app.backoffice.repository;

import app.backoffice.dto.RefundDto;
import org.springframework.stereotype.Repository;
import support.enums.RefundStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RefundAdminRepository {
    Long countRefund(LocalDateTime requestDateSt, LocalDateTime requestDateEnd, List<RefundStatus> refundStatus);
    List<RefundDto> getRefunds(LocalDateTime requestDateSt, LocalDateTime requestDateEnd, List<RefundStatus> refundStatus, Integer page, Integer pageSize);
}
