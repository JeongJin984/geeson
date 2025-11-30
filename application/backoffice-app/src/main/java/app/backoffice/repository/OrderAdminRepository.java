package app.backoffice.repository;

import app.backoffice.dto.OrderDto;
import org.springframework.stereotype.Repository;
import support.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderAdminRepository {
    Long countOrders(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<OrderStatus> orderStatus);
    List<OrderDto> getOrders(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<OrderStatus> orderStatus, Integer page, Integer pageSize);
}
