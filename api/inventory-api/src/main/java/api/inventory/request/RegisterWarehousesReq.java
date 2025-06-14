package api.inventory.request;

import java.util.List;

import domain.inventory.domain.entity.WarehouseJpaEntity;

public record RegisterWarehousesReq(
        List<Warehouse> warehouses) {
    public record Warehouse(
            String name,
            String location,
            Integer capacity) {
        public WarehouseJpaEntity toEntity() {
            return WarehouseJpaEntity.builder()
                    .name(this.name)
                    .location(this.location)
                    .capacity(this.capacity)
                    .build();
        }
    }

    public WarehouseJpaEntity toOneEntity() {
        if (warehouses == null || warehouses.isEmpty()) {
            throw new IllegalArgumentException("No warehouse to register.");
        }
        return warehouses.get(0).toEntity();
    }

    public List<WarehouseJpaEntity> toEntities() {
        return warehouses.stream()
                .map(Warehouse::toEntity)
                .toList();
    }
}