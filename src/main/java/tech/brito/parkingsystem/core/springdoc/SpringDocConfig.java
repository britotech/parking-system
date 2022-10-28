package tech.brito.parkingsystem.core.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    final String securitySchemeName = "bearerAuth";

    @Bean
    public OpenAPI openAPI() {
        var info = new Info().title("Parking system API").version("v1").description("Rest API for parking");
        return new OpenAPI()
                .info(info)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                                                                new SecurityScheme()
                                                                        .name(securitySchemeName)
                                                                        .type(SecurityScheme.Type.HTTP)
                                                                        .scheme("bearer")
                                                                        .bearerFormat("JWT")));
    }
}
