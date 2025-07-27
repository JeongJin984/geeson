package domain.inventory.domain.repository;

import domain.inventory.domain.entity.InventoryJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository {
    Optional<InventoryJpaEntity> findById(Long id);
    List<InventoryJpaEntity> findByProductId(Long productId);
    
    /**
     * Save an inventory entity
     * @param entity The inventory entity to save
     * @return The saved inventory entity
     */
    InventoryJpaEntity save(InventoryJpaEntity entity);
}
