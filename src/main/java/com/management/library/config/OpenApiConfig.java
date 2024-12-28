package com.management.library.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OpenApiConfig {
    public static final String BEARER_SCHEME = "Bearer";
    private static final String API_VERSION = "v1";
    private static final String API_TITLE = "Library API";
    private static final String API_DESCRIPTION = "Library API";
    @Value("${server.url:http://localhost:8080}")
    private String server;
    @Value("${server.port}")
    private Integer port;

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url("http://localhost:" + port),
                        new Server().url(server + ":" + port)
                ))
                .info(
                        new Info()
                                .title(API_TITLE)
                                .description(API_DESCRIPTION)
                                .version(API_VERSION)
                )
                .components(
                        new Components()
                                .addSecuritySchemes(BEARER_SCHEME, new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .name(BEARER_SCHEME))
                );
    }
}
