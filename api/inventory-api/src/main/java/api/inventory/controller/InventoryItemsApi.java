package api.inventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.inventory.request.RegisterInventoryItemReq;
import api.inventory.response.RegisterInventoryItemRes;
import app.inventory.app.InventoryItemsRegisterApp;
import domain.inventory.domain.entity.InventoryItemsJpaEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import app.inventory.command.RegisterInventoryItemCommand;

@RestController
@RequestMapping("/api/inventory-items")
@RequiredArgsConstructor
public class InventoryItemsApi {
    private final InventoryItemsRegisterApp inventoryItemsRegisterApp;

    @PostMapping
    public ResponseEntity<RegisterInventoryItemRes> registerInventoryItem(
            @RequestBody @Valid RegisterInventoryItemReq req) {
        InventoryItemsJpaEntity entity = inventoryItemsRegisterApp
                .registerInventoryItem(new RegisterInventoryItemCommand(req.inventoryId(), req.batchLotId(),
                    req.serialNumber(), req.status()));
        return ResponseEntity.status(HttpStatus.CREATED).body(RegisterInventoryItemRes.from(entity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegisterInventoryItemRes> findInventoryItemById(@PathVariable Long id) {
        InventoryItemsJpaEntity entity = inventoryItemsRegisterApp.findInventoryItemById(id);
        return ResponseEntity.ok(RegisterInventoryItemRes.from(entity));
    }
}
