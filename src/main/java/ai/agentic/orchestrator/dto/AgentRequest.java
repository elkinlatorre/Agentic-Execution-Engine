package ai.agentic.orchestrator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Estructura de petición para el agente")
public record AgentRequest(
        @Schema(description = "Pregunta o instrucción para el orquestador", example = "Calcula el impuesto de renta para un salario de 5000 USD")
        @NotBlank(message = "El prompt no puede estar vacío")
        String prompt
) {}
