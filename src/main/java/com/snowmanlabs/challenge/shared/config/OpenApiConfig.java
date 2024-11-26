package com.snowmanlabs.challenge.shared.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Snowman Labs")
                        .version("1.0.0")
                        .description("Api criada para desafio técnico para vaga de Software Engineer"));
    }

}
