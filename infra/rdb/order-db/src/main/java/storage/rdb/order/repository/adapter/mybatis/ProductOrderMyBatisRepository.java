package storage.rdb.order.repository.adapter.mybatis;

import app.backoffice.dto.OrderDto;
import org.apache.ibatis.annotations.Mapper;
import support.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ProductOrderMyBatisRepository {
    Long countOrders(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<OrderStatus> orderStatus);
    List<OrderDto> getOrders(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<OrderStatus> orderStatus, Integer page, Integer pageSize);
}
