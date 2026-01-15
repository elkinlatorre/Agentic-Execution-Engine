package ai.agentic.orchestrator.service;

import ai.agentic.orchestrator.dto.ExecutionLogDTO;
import ai.agentic.orchestrator.dto.OrchestrationResult;

import java.util.List;

public interface OrchestratorService {
    OrchestrationResult process(String userPrompt);
    List<ExecutionLogDTO> getAllHistory();
}
