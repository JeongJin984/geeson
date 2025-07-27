package app.inventory.app;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.inventory.command.RegisterInventoryItemCommand;
import domain.inventory.domain.entity.InventoryItemsJpaEntity;
import domain.inventory.domain.entity.InventoryJpaEntity;
import domain.inventory.domain.repository.InventoryItemsRepository;
import domain.inventory.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryItemsRegisterApp {

    private final InventoryItemsRepository inventoryItemsRepository;
    private final InventoryRepository inventoryRepository;

    public InventoryItemsJpaEntity registerInventoryItem(RegisterInventoryItemCommand command) {
        InventoryJpaEntity inventory = inventoryRepository.findById(command.inventoryId())
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found: id=" + command.inventoryId()));

        InventoryItemsJpaEntity entity = InventoryItemsJpaEntity.from(
                inventory,
                command.batchLotId(),
                command.serialNumber(),
                command.status());

        return inventoryItemsRepository.save(entity);
    }

    /**
     * Find an inventory item by its ID.
     * 
     * @param id The ID of the inventory item.
     * @return The found inventory item entity, or null if not found.
     */
    public InventoryItemsJpaEntity findInventoryItemById(Long id) {
        return inventoryItemsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory Item not found: id=" + id));
    }

}