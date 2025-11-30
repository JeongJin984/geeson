package app.backoffice.repository;

import org.springframework.stereotype.Repository;
import support.enums.SettlementStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SettlementAdminRepository {
    Long countSettlement(LocalDateTime remitDtSt, LocalDateTime remitDtEd, List<SettlementStatus> status);
}
