package storage.rdb.payment.repository;

import app.backoffice.dto.RefundDto;
import app.backoffice.repository.RefundAdminRepository;
import domain.payment.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import storage.rdb.payment.repository.adapter.jpa.SpringDataRefundJpaRepository;
import storage.rdb.payment.repository.adapter.mybatis.RefundMybatisRepository;
import support.enums.RefundStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RefundJpaRepository implements RefundRepository, RefundAdminRepository {
    private final SpringDataRefundJpaRepository repository;
    private final RefundMybatisRepository mybatisRepository;

    @Override
    public Long countRefund(LocalDateTime requestDateSt, LocalDateTime requestDateEnd, List<RefundStatus> refundStatus) {
        return mybatisRepository.countRefund(requestDateSt, requestDateEnd, refundStatus);
    }

    @Override
    public List<RefundDto> getRefunds(LocalDateTime requestDateSt, LocalDateTime requestDateEnd, List<RefundStatus> refundStatus, Integer page, Integer pageSize) {
        return mybatisRepository.getRefunds(requestDateSt, requestDateEnd, refundStatus, page, pageSize);
    }
}
