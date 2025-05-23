package redis.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
@Import(RedissonConfig.class)
@RequiredArgsConstructor
public class RedisLockService {
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedissonConfig config;

    public RedisLockService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void doSomethingWithLock(String lockKey) throws IOException {
        if (redissonClient == null) {
            redissonClient = config.redissonClient();
        }
        RLock lock = redissonClient.getLock("lock:" + lockKey);
        boolean acquired = false;
        try {
            // 대기 최대 5초, 점유 10초
            acquired = lock.tryLock(5, 10, TimeUnit.SECONDS);
            if (acquired) {
                System.out.println("Lock acquired!");
            } else {
                System.out.println("Lock not acquired.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            if (acquired) {
                lock.unlock();
                System.out.println("Lock released!");
            }
        }
    }
}
