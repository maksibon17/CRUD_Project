package com.CRUD_Project.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info()
                        .title("CRUD-приложение")
                        .description("CRUD-приложение с применением технологий: Java, SpringBoot, PostgreSQL, JPA, " +
                                "Liquibase, REST API, Swagger")
                        .version("1.0")
                );
    }
}
