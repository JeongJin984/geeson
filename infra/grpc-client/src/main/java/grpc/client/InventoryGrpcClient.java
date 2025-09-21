package grpc.client;

import grpc.inventory.AddInventoryRequest;
import grpc.inventory.AddInventoryResponse;
import grpc.inventory.SelectInventoryRequest;
import grpc.inventory.SelectInventoryResponse;
import grpc.inventory.SelectInventoriesRequest;
import grpc.inventory.SelectInventoriesResponse;
import grpc.inventory.InventoryServiceGrpc;
import grpc.inventory.InventoryServiceGrpc.InventoryServiceBlockingStub;
import grpc.inventory.InventoryCheck;
import grpc.inventory.InventoryResult;
import grpc.client.dto.OrderRegister;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryGrpcClient {

    private ManagedChannel channel;
    private InventoryServiceBlockingStub inventoryStub;

    @PostConstruct
    public void init() {
        this.channel = ManagedChannelBuilder
                .forAddress("grpc-server", 6565) // docker-compose service alias
                .usePlaintext()
                .build();

        this.inventoryStub = InventoryServiceGrpc.newBlockingStub(channel);
    }

    /** gRPC 서버에 새로운 Inventory 추가 */
    public AddInventoryResponse addInventory(Long productId,
            Long warehouseId,
            int totalQuantity,
            int reorderLevel,
            int reorderQuantity) {
        AddInventoryRequest request = AddInventoryRequest.newBuilder()
                .setProductId(productId)
                .setWarehouseId(warehouseId)
                .setTotalQuantity(totalQuantity)
                .setReorderLevel(reorderLevel)
                .setReorderQuantity(reorderQuantity)
                .build();

        try {
            return inventoryStub.addInventory(request);
        } catch (StatusRuntimeException e) {
            System.err.println("❌ gRPC addInventory failed: " + e.getStatus());
            throw e;
        }
    }

    /** gRPC 서버에서 특정 상품의 사용 가능한 재고 조회 */
    public SelectInventoryResponse selectInventory(Long productId, int quantity) {
        SelectInventoryRequest request = SelectInventoryRequest.newBuilder()
                .setProductId(productId)
                .setQuantity(quantity)
                .build();

        try {
            return inventoryStub.selectInventory(request);
        } catch (StatusRuntimeException e) {
            System.err.println("❌ gRPC selectInventory failed: " + e.getStatus());
            throw e;
        }
    }

    public Map<Long, Boolean> checkInventories(List<OrderRegister> items) {
        List<InventoryCheck> checks = items.stream()
                .map(item -> InventoryCheck.newBuilder()
                        .setProductId(item.productId())
                        .setQuantity(item.quantity())
                        .build())
                .toList();

        SelectInventoriesRequest request = SelectInventoriesRequest.newBuilder()
                .addAllChecks(checks)
                .build();

        SelectInventoriesResponse response = inventoryStub.selectInventories(request);

        return response.getResultsList().stream()
                .collect(Collectors.toMap(
                        InventoryResult::getProductId,
                        InventoryResult::getAvailable));
    }

    @PreDestroy
    public void shutdown() {
        if (channel != null) {
            channel.shutdown();
        }
    }
}
