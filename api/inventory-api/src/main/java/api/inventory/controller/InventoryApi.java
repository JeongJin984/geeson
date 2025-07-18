package api.inventory.controller;

import app.inventory.app.InventoryAddApp;
import api.inventory.request.AddInventoryReq;
import api.inventory.response.AddInventoryRes;
import domain.inventory.domain.entity.InventoryJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryApi {

    private final InventoryAddApp inventoryAddApp;

    /**
     * Add new inventory for a product in a warehouse
     */
    @PostMapping
    public ResponseEntity<AddInventoryRes> addInventory(@RequestBody @Valid AddInventoryReq req) {
        InventoryJpaEntity entity = inventoryAddApp.addInventory(
            req.productId(),
            req.warehouseId(),
            req.totalQuantity(),
            req.reorderLevel(),
            req.reorderQuantity()
        );

        AddInventoryRes res = AddInventoryRes.from(entity);
        return ResponseEntity.status(201).body(res);
    }
}