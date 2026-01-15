package ai.agentic.orchestrator.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AiConfig {
    @Bean
    public ChatLanguageModel chatModel(
            @Value("${ollama.base-url}") String baseUrl,
            @Value("${ollama.model-name}") String modelName) {

        return OllamaChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .timeout(Duration.ofSeconds(60)) // Tiempo de espera para inferencia lenta en CPU
                .build();
    }
}
