package storage.rdb.payment.repository;

import app.backoffice.dto.PaymentDto;
import app.backoffice.repository.PaymentAdminRepository;
import domain.payment.entity.PaymentJpaEntity;
import domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import storage.rdb.payment.repository.adapter.jpa.SpringDataPaymentJpaRepository;
import storage.rdb.payment.repository.adapter.mybatis.PaymentMybatisRepository;
import support.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentJpaRepository implements PaymentRepository, PaymentAdminRepository {
    private final SpringDataPaymentJpaRepository repository;
    private final PaymentMybatisRepository mybatisRepository;

    @Override
    public PaymentJpaEntity save(PaymentJpaEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<PaymentJpaEntity> findByOrderId(String orderId) {
        return repository.findByOrderId(orderId);
    }
    
    @Override
    public List<PaymentJpaEntity> findByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public BigDecimal sumPaymentAmount(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<PaymentStatus> paymentStatus) {
        return mybatisRepository.sumPaymentAmount(reqDtSt, reqDtEd, paymentStatus);
    }

    @Override
    public Long countPayment(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<PaymentStatus> paymentStatus) {
        return mybatisRepository.countPayment(reqDtSt, reqDtEd, paymentStatus);
    }

    @Override
    public List<PaymentDto> getPayments(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<PaymentStatus> paymentStatus, Integer page, Integer pageSize) {
        return mybatisRepository.getPayments(reqDtSt, reqDtEd, paymentStatus, page, pageSize);
    }
}