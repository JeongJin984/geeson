package api.inventory.controller;

import domain.inventory.service.WarehouseService;
import domain.inventory.domain.entity.WarehouseJpaEntity;
import api.inventory.request.RegisterWarehousesReq;
import api.inventory.response.RegisterWarehouseRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class WarehouseApi {

    private final WarehouseService warehouseService;

    @PostMapping
    public RegisterWarehouseRes register(@RequestBody RegisterWarehousesReq req) {
        WarehouseJpaEntity warehouse = warehouseService.register(req.toOneEntity());
        return RegisterWarehouseRes.from(warehouse);
    }

    @GetMapping
    public List<RegisterWarehouseRes> list() {
        return warehouseService.findAll().stream()
                .map(RegisterWarehouseRes::from)
                .toList();
    }

    /**
     * 창고 삭제(단일 데이터)
     * DELETE /api/warehouse/delete?id=1
     * 
     * @param id
     */
    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        warehouseService.deleteById(id);
    }
}