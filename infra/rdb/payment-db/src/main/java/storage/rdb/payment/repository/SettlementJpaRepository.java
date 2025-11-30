package storage.rdb.payment.repository;

import app.backoffice.repository.SettlementAdminRepository;
import domain.payment.repository.SettlementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import storage.rdb.payment.repository.adapter.jpa.SpringDataSettlementJpaRepository;
import storage.rdb.payment.repository.adapter.mybatis.SettlementMybatisRepository;
import support.enums.SettlementStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SettlementJpaRepository implements SettlementRepository, SettlementAdminRepository {
    private final SpringDataSettlementJpaRepository repository;
    private final SettlementMybatisRepository mybatisRepository;

    @Override
    public Long countSettlement(LocalDateTime remitDtSt, LocalDateTime remitDtEd, List<SettlementStatus> status) {
        return mybatisRepository.countSettlement(remitDtSt, remitDtEd, status);
    }
}