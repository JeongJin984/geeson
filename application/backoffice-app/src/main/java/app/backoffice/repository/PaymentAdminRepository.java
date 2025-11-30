package app.backoffice.repository;

import app.backoffice.dto.PaymentDto;
import org.springframework.stereotype.Repository;
import support.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentAdminRepository {
    BigDecimal sumPaymentAmount(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<PaymentStatus> paymentStatus);
    Long countPayment(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<PaymentStatus> paymentStatus);
    List<PaymentDto> getPayments(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<PaymentStatus> paymentStatus, Integer page, Integer pageSize);
}
