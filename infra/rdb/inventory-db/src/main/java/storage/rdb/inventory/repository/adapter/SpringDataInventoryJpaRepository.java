package storage.rdb.inventory.repository.adapter;

import domain.inventory.domain.entity.InventoryJpaEntity;
import domain.inventory.domain.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataInventoryJpaRepository extends JpaRepository<InventoryJpaEntity, Long> {
    
    @Query("select i from InventoryJpaEntity i inner join i.product p where p.productId = :productId")
    List<InventoryJpaEntity> findAllByProductId(Long productId);

    // ✅ 여러 productId 조회
    @Query("select i from InventoryJpaEntity i inner join i.product p where p.productId in :productIds")
    List<InventoryJpaEntity> findAllByProductIdIn(List<Long> productIds);

    @Query("select i from InventoryJpaEntity i " +
           "where i.product.productId = :productId and i.warehouseId = :warehouseId")
    Optional<InventoryJpaEntity> findByProductIdAndWarehouseId(Long productId, Long warehouseId);

    @Query("""
        select i from InventoryJpaEntity i 
        where (i.product.productId, i.warehouseId) in :pairs
    """)
    List<InventoryJpaEntity> findAllByProductIdAndWarehouseIdIn(List<Object[]> pairs);
}
