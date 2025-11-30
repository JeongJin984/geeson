package storage.rdb.payment.repository.adapter.mybatis;

import app.backoffice.dto.PaymentDto;
import org.apache.ibatis.annotations.Mapper;
import support.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface PaymentMybatisRepository {

    BigDecimal sumPaymentAmount(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<PaymentStatus> paymentStatus);

    Long countPayment(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<PaymentStatus> paymentStatus);

    List<PaymentDto> getPayments(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<PaymentStatus> paymentStatus, Integer page, Integer pageSize);
}
