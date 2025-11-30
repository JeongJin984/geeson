package app.backoffice.application;

import app.backoffice.dto.PaymentDto;
import app.backoffice.repository.PaymentAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import support.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentSelectApp {
    private final PaymentAdminRepository paymentAdminRepository;

    public BigDecimal sumPaymentAmount(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<PaymentStatus> paymentStatus) {
        return paymentAdminRepository.sumPaymentAmount(reqDtSt, reqDtEd, paymentStatus);
    }

    public Long countPaymentAmount(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<PaymentStatus> paymentStatus) {
        return paymentAdminRepository.countPayment(reqDtSt, reqDtEd, paymentStatus);
    }

    public List<PaymentDto> getPayments(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<PaymentStatus> paymentStatus, Integer page, Integer pageSize) {
        return paymentAdminRepository.getPayments(reqDtSt, reqDtEd, paymentStatus, page, pageSize);
    }
}
