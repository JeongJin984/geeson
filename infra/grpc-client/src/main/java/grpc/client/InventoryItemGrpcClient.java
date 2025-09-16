package grpc.client;

import grpc.inventory.CreateInventoryItemRequest;
import grpc.inventory.InventoryItemResponse;
import grpc.inventory.InventoryItemServiceGrpc;
import grpc.inventory.InventoryItemServiceGrpc.InventoryItemServiceBlockingStub;
import grpc.inventory.GetInventoryItemRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class InventoryItemGrpcClient {

    private InventoryItemServiceBlockingStub inventoryItemStub;

    @PostConstruct
    public void init() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("grpc-server", 6565) // gRPC 서버 주소와 포트
                .usePlaintext()                  // TLS 미사용 (테스트 환경)
                .build();

        inventoryItemStub = InventoryItemServiceGrpc.newBlockingStub(channel);
    }

    /**
     * gRPC 서버에서 단일 InventoryItem 조회
     *
     * @param inventoryItemId 아이템 ID
     * @return InventoryItemResponse
     */
    public InventoryItemResponse getInventoryItem(Long inventoryItemId) {
        GetInventoryItemRequest request = GetInventoryItemRequest.newBuilder()
                .setInventoryItemId(inventoryItemId)
                .build();

        return inventoryItemStub.getInventoryItem(request);
    }

    /**
     * gRPC 서버에 새로운 InventoryItem 생성 요청
     *
     * @param inventoryId   인벤토리 ID
     * @param serialNumber  시리얼 넘버
     * @param status        상태 (AVAILABLE, RESERVED 등)
     * @return InventoryItemResponse
     */
    public InventoryItemResponse createInventoryItem(Long inventoryId, String serialNumber, String status) {
        CreateInventoryItemRequest request = CreateInventoryItemRequest.newBuilder()
                .setInventoryId(inventoryId)
                .setSerialNumber(serialNumber != null ? serialNumber : "")
                .setStatus(status != null ? status : "AVAILABLE")
                .build();

        return inventoryItemStub.createInventoryItem(request);
    }
}
