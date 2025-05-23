package redis.lock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.redisson.api.RedissonClient;
import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest(classes = RedisLockService.class)
@ExtendWith(MockitoExtension.class)
public class RedisLockServiceTest {

    /**
     * Service Lock이 잘 동작하나 확인
     * 
     * @throws Exception
     */
    @Test
    void testSingleLockConcurrency() throws Exception {
        RedissonClient mockClient = mock(RedissonClient.class);
        RLock mockLock = mock(RLock.class);

        when(mockClient.getLock("lock:test")).thenReturn(mockLock);
        when(mockLock.tryLock(5, 10, TimeUnit.SECONDS)).thenReturn(true);

        RedisLockService redisLockService = new RedisLockService(mockClient);
        redisLockService.doSomethingWithLock("test");

        // Lock풀렸나 확인
        verify(mockLock).unlock();
    }

    /**
     * @todo 다중Lock테스트 추가
     */
    void testDistributedLockConcurrency() {
    }
}