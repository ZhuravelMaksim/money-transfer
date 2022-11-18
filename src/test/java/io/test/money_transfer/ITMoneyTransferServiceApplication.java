package io.test.money_transfer;

import io.test.money_transfer.utils.PostgresDbCleaner;
import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RScheduledFuture;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@EnableJpaAuditing
@EnableConfigurationProperties
@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class),
        @ComponentScan.Filter(type = FilterType.ANNOTATION, value = SpringBootApplication.class)}
)
@SpringBootApplication(exclude = {
        RedisAutoConfiguration.class
})
public class ITMoneyTransferServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ITMoneyTransferServiceApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Bean
    public PostgresDbCleaner postgresDbCleaner(final DataSource dataSource) {
        return new PostgresDbCleaner(dataSource);
    }

    @Bean
    public RScheduledExecutorService testRedissonExecutorService() {
        var executor = mock(RScheduledExecutorService.class);
        var testTask = mock(RScheduledFuture.class);
        when(testTask.getTaskId()).thenReturn(UUID.randomUUID().toString());
        when(executor.schedule(any(Runnable.class), anyLong(), any(TimeUnit.class))).thenReturn(testTask);
        return executor;
    }

    @Bean
    public RedissonClient redissonClient(final RScheduledExecutorService executorService) {
        var redissonClient = mock(RedissonClient.class);
        when(redissonClient.getMap(anyString())).thenReturn(new TestRedisMap<>());
        when(redissonClient.getMap(anyString(), any(Codec.class))).thenReturn(new TestRedisMap<>());
        when(redissonClient.getMapCache(anyString())).thenReturn(new TestRedisMap<>());
        when(redissonClient.getMapCache(anyString(), any(Codec.class))).thenReturn(new TestRedisMap<>());
        when(redissonClient.getLock(anyString())).thenReturn(new TestRedisLock());
        when(redissonClient.getSet(anyString())).thenReturn(new TestRedisSet<>());
        when(redissonClient.getExecutorService(anyString())).thenReturn(executorService);
        return redissonClient;
    }
}
