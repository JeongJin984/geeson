package storage.rdb.order.repository;

import app.backoffice.dto.OrderDto;
import app.backoffice.repository.OrderAdminRepository;
import domain.order.entity.ProductOrderJpaEntity;
import domain.order.repository.ProductOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import storage.rdb.order.repository.adapter.jpa.SpringDataProductOrderJpaRepository;
import storage.rdb.order.repository.adapter.mybatis.ProductOrderMyBatisRepository;
import support.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductOrderJpaRepository implements ProductOrderRepository, OrderAdminRepository {
    private final SpringDataProductOrderJpaRepository repository;
    private final ProductOrderMyBatisRepository myBatisRepository;

    @Override
    public ProductOrderJpaEntity save(ProductOrderJpaEntity entity) {
        return repository.save(entity);
    }

    @Override
    public List<ProductOrderJpaEntity> getAllOrders(int page, int size) {
        return repository.findAll();
    }

    @Override
    public Optional<ProductOrderJpaEntity> findById(Long id) {
        return repository.findById(id);
    }
    
    @Override
    public List<ProductOrderJpaEntity> findByCustomerId(Long customerId) {
        return repository.findByCustomer_CustomerId(customerId);
    }

    @Override
    public Long countOrders(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<OrderStatus> orderStatus) {
        return myBatisRepository.countOrders(reqDtSt, reqDtEd, orderStatus);
    }

    @Override
    public List<OrderDto> getOrders(LocalDateTime reqDtSt, LocalDateTime reqDtEd, List<OrderStatus> orderStatus, Integer page, Integer pageSize) {
        return myBatisRepository.getOrders(reqDtSt, reqDtEd, orderStatus, page, pageSize);
    }
}