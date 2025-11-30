package app.backoffice.application;

import app.backoffice.dto.OrderDto;
import app.backoffice.repository.OrderAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import support.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSelectApp {
    private final OrderAdminRepository orderAdminRepository;

    public Long totalOrderCount(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<OrderStatus> orderStatus) {
        return orderAdminRepository.countOrders(reqDtSt, reqDtEd, orderStatus);
    }

    public List<OrderDto> getOrders(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<OrderStatus> orderStatus, Integer page, Integer pageSize) {
        return orderAdminRepository.getOrders(reqDtSt, reqDtEd, orderStatus, page, pageSize);
    }
}
