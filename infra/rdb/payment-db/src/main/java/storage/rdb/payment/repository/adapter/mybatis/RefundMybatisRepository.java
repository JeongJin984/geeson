package storage.rdb.payment.repository.adapter.mybatis;

import app.backoffice.dto.RefundDto;
import org.apache.ibatis.annotations.Mapper;
import support.enums.RefundStatus;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface RefundMybatisRepository {
    Long countRefund(LocalDateTime requestDateSt, LocalDateTime requestDateEnd, List<RefundStatus> refundStatus);

    List<RefundDto> getRefunds(LocalDateTime requestDateSt, LocalDateTime requestDateEnd, List<RefundStatus> refundStatus, Integer page, Integer pageSize);

}
