package api.inventory;

import api.inventory.request.RegisterWarehousesReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.inventory.domain.entity.WarehouseJpaEntity;
import domain.inventory.domain.repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.springframework.boot.autoconfigure.domain.EntityScan;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = api.inventory.InventoryApiMain.class)
@AutoConfigureMockMvc
@ActiveProfiles("test") //application-test
@EntityScan(basePackages = "domain.inventory.domain.entity")
class WarehouseApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() {
        warehouseRepository.deleteAll(); // 테스트 전 초기화
    }

    @Test
    void checkTables() {
        List<String> tables = em.createNativeQuery(
            "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'"
        ).getResultList();
        
        System.out.println("🔍 Created tables: " + tables);
    }

    @Test
    void 창고_등록_성공() throws Exception {
        RegisterWarehousesReq req = new RegisterWarehousesReq("서울창고", "서울 강남구", 1000);

        mockMvc.perform(post("/api/warehouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("서울창고"));
    }

    @Test
    void 창고_목록_조회() throws Exception {
        warehouseRepository.save(WarehouseJpaEntity.builder()
                .name("A창고")
                .location("부산")
                .capacity(500)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());

        warehouseRepository.save(WarehouseJpaEntity.builder()
                .name("B창고")
                .location("대전")
                .capacity(800)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());

        mockMvc.perform(get("/api/warehouses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name", anyOf(is("A창고"), is("B창고"))));
    }

    @Test 
    void 창고_삭제_성공() throws Exception {
        WarehouseJpaEntity entity = warehouseRepository.save(WarehouseJpaEntity.builder()
                .name("삭제용창고")
                .location("제주2")
                .capacity(200)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());

        mockMvc.perform(delete("/api/warehouses/" + entity.getWarehouseId()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/warehouses"))
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void 창고_등록_실패_이름_누락() throws Exception {
        // 이름이 null이면 등록 실패 (validation 대상이 된다면)
        RegisterWarehousesReq req = new RegisterWarehousesReq(null, "서울 강남구", 1000);

        mockMvc.perform(post("/api/warehouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest()); // 또는 400
    }

    @Test
    void 창고_등록_실패_용량_음수() throws Exception {
        RegisterWarehousesReq req = new RegisterWarehousesReq("서울창고", "서울", -10);

        mockMvc.perform(post("/api/warehouses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 존재하지_않는_창고_삭제_실패() throws Exception {
        Long nonexistentId = 999L;

        mockMvc.perform(delete("/api/warehouses/" + nonexistentId))
                .andExpect(status().isNotFound()); // 404 처리하는 경우
    }
}