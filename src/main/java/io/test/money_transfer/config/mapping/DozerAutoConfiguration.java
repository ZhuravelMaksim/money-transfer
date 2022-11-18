package io.test.money_transfer.config.mapping;

import com.github.dozermapper.core.CustomConverter;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class DozerAutoConfiguration {

    @Bean
    public Mapper mapper(final List<BeanMappingBuilder> beanMappingBuilders, final Optional<List<CustomConverter>> customConverters) {
        return DozerBeanMapperBuilder.create()
                .withCustomConvertersWithIds(customConverters.map(this::getCustomConvertersWithId).orElse(null))
                .withMappingBuilders(beanMappingBuilders)
                .build();
    }

    private Map<String, CustomConverter> getCustomConvertersWithId(final List<CustomConverter> customConverters) {
        return customConverters.stream().collect(Collectors.toMap(it -> it.getClass().getSimpleName(), Function.identity()));
    }
}
