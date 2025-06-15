package api.inventory.request;

import java.util.List;

import domain.inventory.domain.entity.WarehouseJpaEntity;

public record RegisterWarehousesReq(String name, String location, Integer capacity) {
    public WarehouseJpaEntity toOneEntity() {
        return WarehouseJpaEntity.builder()
            .name(name)
            .location(location)
            .capacity(capacity)
            .build();
    }
}
