package tech.brito.parkingsystem.core.springdoc;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI(){
        var info = new Info().title("Parking system API").version("v1").description("Rest API for parking");
        return new OpenAPI().info(info);
    }
}
