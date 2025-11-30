package storage.rdb.payment.repository.adapter.mybatis;

import org.apache.ibatis.annotations.Mapper;
import support.enums.SettlementStatus;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface SettlementMybatisRepository {
    Long countSettlement(LocalDateTime remitDtSt, LocalDateTime remitDtEd, List<SettlementStatus> status);
}
