package ai.agentic.orchestrator.dto;

import java.time.LocalDateTime;

public record AgentResponse(
        String message,
        String status,
        String processedBy,
        LocalDateTime timestamp
) {
    public static AgentResponse of(String message, String status, String processedBy) {
        return new AgentResponse(message, status, processedBy, LocalDateTime.now());
    }
}
