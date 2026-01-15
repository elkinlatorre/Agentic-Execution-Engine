package ai.agentic.orchestrator.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AiConfig {
    @Bean(name = "routerModel")
    public ChatLanguageModel routerModel(
            @Value("${ollama.base-url}") String baseUrl,
            @Value("${ollama.model.router}") String modelName) {
        return OllamaChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .temperature(0.0) // Queremos consistencia para el router
                .build();
    }

    @Bean(name = "coderModel")
    public ChatLanguageModel coderModel(
            @Value("${ollama.base-url}") String baseUrl,
            @Value("${ollama.model.coder}") String modelName) {
        return OllamaChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .temperature(0.2) // Un poco de creatividad para resolver problemas
                .timeout(Duration.ofSeconds(120))
                .build();
    }
}
