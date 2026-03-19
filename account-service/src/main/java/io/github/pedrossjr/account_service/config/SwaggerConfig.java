package io.github.pedrossjr.account_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("${application.description}") String appDescription) {
        return new OpenAPI()
            .info(new Info()
                .title("Minha API de Account Service")
                .version("v1")
                .description(appDescription));
    }
}