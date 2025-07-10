package redis.lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisLockService {

    private RedissonClient redissonClient;

    /**
     *  Spring DI 안되는 외부라이브러리 -> 테스트 위해 생성자를 명시적으로 작성
     * @param redissonClient
     */
    public RedisLockService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public boolean doSomethingWithLock(String key, int waitTime, int leaseTime, TimeUnit unit) throws InterruptedException {
        RLock lock = redissonClient.getLock("lock:" + key);
        boolean acquired = lock.tryLock(waitTime, leaseTime, unit);
        if (!acquired) {
            return false;
        }
        try {
            // 실제 처리 로직
            System.out.println("🔒 Lock acquired by " + Thread.currentThread().getName());
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                System.out.println("🔓 Lock released by " + Thread.currentThread().getName());
            }
        }
        return true;
    }
}