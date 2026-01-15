package ai.agentic.orchestrator.api;

import ai.agentic.orchestrator.dto.AgentRequest;
import ai.agentic.orchestrator.dto.AgentResponse;
import ai.agentic.orchestrator.dto.ExecutionLogDTO;
import ai.agentic.orchestrator.dto.OrchestrationResult;
import ai.agentic.orchestrator.service.OrchestratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agent")
@RequiredArgsConstructor
@Tag(name = "Agent Orchestrator", description = "Main endpoint for AI Agent Orchestration")
public class OrchestratorController {

    private final OrchestratorService orchestratorService;

    @Operation(summary = "Ask a question to the Agentic Workflow")
    @PostMapping("/ask")
    public ResponseEntity<AgentResponse> ask(@Valid @RequestBody AgentRequest request) {
        // Virtual threads in Java 25 handle the blocking I/O from the LLM efficiently
        OrchestrationResult result = orchestratorService.process(request.prompt());
        return ResponseEntity.ok(AgentResponse.of(
                result.output(),
                "COMPLETED",
                result.agentName()
        ));
    }

    @GetMapping("/history")
    public ResponseEntity<List<ExecutionLogDTO>> getHistory() {
        return ResponseEntity.ok(orchestratorService.getAllHistory());
    }
}
