package org.seoultech.tableapi.global.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("ST:table API Document")
                .version("1.0")
                .description(
                        "환영합니다! [ST:table](https://example.com)는 서울과학기술대학교 제1학생회관 푸드코트 온라인 주문 서비스입니다. 이 API 문서는 ST:table의 API를 사용하는 방법을 설명합니다.\n")
                .contact(new io.swagger.v3.oas.models.info.Contact().email("easy1nhard2@gmail.com"));

        //JWT 인증 방식 설정
        String jwtScheme = "jwtAuth";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtScheme);
        Components components = new Components()
                .addSecuritySchemes(jwtScheme, new SecurityScheme()
                        .name("Authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .in(SecurityScheme.In.HEADER)
                        .scheme("Bearer")
                        .bearerFormat("JWT"));

        //Swagger UI 설정 및 보안 추가
        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080"))
                .components(new Components())
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
