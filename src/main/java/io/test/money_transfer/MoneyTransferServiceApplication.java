package io.test.money_transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@EnableAsync
@EnableJpaAuditing
@EnableConfigurationProperties
@SpringBootApplication
public class MoneyTransferServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MoneyTransferServiceApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
