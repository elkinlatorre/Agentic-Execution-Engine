package ai.agentic.orchestrator.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Agentic AI Workflow API")
                        .version("1.0.0")
                        .description("Proof of concept for agent orchestration with Java 25, Spring Boot 3, and ephemeral code execution.")
                        .contact(new Contact()
                                .name("Elkin Latorre")
                                .url("https://github.com/elkinlatorre"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
