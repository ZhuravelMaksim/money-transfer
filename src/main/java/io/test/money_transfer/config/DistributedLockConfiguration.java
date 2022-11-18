package io.test.money_transfer.config;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class DistributedLockConfiguration {

    private final RedissonClient redissonClient;

    @Bean
    public Function<String, RLock> accountBalanceLock() {
        return id -> redissonClient.getLock("transfer.accountBalanceLock#" + id.toLowerCase());
    }
}
