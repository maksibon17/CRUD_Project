package com.CRUD_Project.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("CRUD-приложение")
                        .description("Документация к CRUD-приложению с применением технологий: Java, SpringBoot, " +
                                "PostgreSQL, JPA, Liquibase, REST API, Swagger.")
                        .version("v0.0.1")
                        .contact(new Contact()
                                .name("ст гр 22ВП2 Паткина А, Гришин М")
                                .email("maksgrishinnomer160@gmail.com")
                                .url("https://www.youtube.com/watch?v=dQw4w9WgXcQ")))
                .servers(Collections.singletonList(new Server()
                        .url("http://localhost:8080")
                        .description("Локальный сервер для разработки")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}
