package ai.agentic.orchestrator.service;

public interface CodeGeneratorAgent {
    String generatePythonCode(String taskDescription);
    String healPythonCode(String taskDescription, String failingCode, String errorLog);
}
