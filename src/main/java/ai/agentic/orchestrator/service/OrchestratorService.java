package ai.agentic.orchestrator.service;

import ai.agentic.orchestrator.dto.OrchestrationResult;

public interface OrchestratorService {
    OrchestrationResult process(String userPrompt);
}
