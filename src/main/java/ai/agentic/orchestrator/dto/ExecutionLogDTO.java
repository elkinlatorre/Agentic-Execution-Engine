package ai.agentic.orchestrator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExecutionLogDTO {
    private Long id;
    private String userPrompt;
    private String detectedIntent;
    private String generatedCode;
    private String executionResult;
    private String finalAgent;
    private Integer retryCount;
}
