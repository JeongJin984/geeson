package redis.lock;

import java.io.IOException;
import java.io.InputStream;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson Client 설정 파일
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() throws IOException {
        InputStream config = getClass().getClassLoader().getResourceAsStream("redisson.yaml");
        Config yamlConfig = Config.fromYAML(config);
        return Redisson.create(yamlConfig);
    }
}