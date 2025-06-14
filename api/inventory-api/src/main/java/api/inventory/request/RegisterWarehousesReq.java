package api.inventory.request;

import java.util.List;

import domain.inventory.domain.entity.WarehouseJpaEntity;

public record RegisterWarehousesReq(
    List<Warehouse> warehouses
) {
    public record Warehouse(
        String name,
        String location,
        Integer capacity
    ) {
        public WarehouseJpaEntity toEntity() {
            WarehouseJpaEntity entity = new WarehouseJpaEntity();
            entity.setName(this.name);
            entity.setLocation(this.location);
            entity.setCapacity(this.capacity);
            return entity;
        }
    }

    public WarehouseJpaEntity toOneEntity() {
        return warehouses.get(0).toEntity();
    }

    public List<WarehouseJpaEntity> toEntities() {
        return warehouses.stream()
            .map(Warehouse::toEntity)
            .toList();
    }
}