package io.test.money_transfer.config.mapping;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonJsonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ObjectMapperBuilderProvider objectMapperBuilderProvider() {
        return new ObjectMapperBuilderProvider();
    }

    @Bean
    @ConditionalOnMissingBean
    @Primary
    public ObjectMapper objectMapper() {
        var jackson2ObjectMapperBuilder = objectMapperBuilderProvider().get();
        return jackson2ObjectMapperBuilder.createXmlMapper(false).build()
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
    }
}
