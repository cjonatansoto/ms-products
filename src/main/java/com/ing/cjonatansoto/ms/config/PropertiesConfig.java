package com.ing.cjonatansoto.ms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:configs/swagger.properties")
})
public class PropertiesConfig {
}