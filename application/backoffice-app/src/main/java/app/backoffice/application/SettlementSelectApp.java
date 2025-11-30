package app.backoffice.application;

import app.backoffice.repository.SettlementAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import support.enums.SettlementStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SettlementSelectApp {
    private final SettlementAdminRepository settlementRepository;

    public Long countSettlement(LocalDateTime remitDtSt, LocalDateTime remitDtEd, List<SettlementStatus> status) {
         return settlementRepository.countSettlement(remitDtSt, remitDtEd, status);
    }
}
