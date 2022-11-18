package io.test.money_transfer.config.mapping;


import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


public class ObjectMapperBuilderProvider {
    public Jackson2ObjectMapperBuilder get() {
        return new Jackson2ObjectMapperBuilder();
    }
}
