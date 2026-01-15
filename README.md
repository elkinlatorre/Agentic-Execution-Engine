🤖 Agentic AI Orchestrator & Sandbox
Java 25 | Spring Boot 4 | LangChain4j | Docker | Virtual Threads
This project is a Technical Proof of Concept (PoC) for an Artificial Intelligence Agent Orchestrator capable of reasoning, generating specialized Python code, and executing it securely within isolated environments (Sandboxing).

🚀 Key Features
Multi-Model Orchestration: Employs a lightweight model (Llama 3.2) for intent routing and a specialized model (DeepSeek-Coder) for complex logic generation.

Secure Docker Sandboxing: Executes AI-generated code inside ephemeral containers with strict resource constraints (512MB RAM, 50% CPU quota) and disabled network access.

Autonomous Self-Healing: If the generated code fails, the agent receives the execution traceback and automatically attempts to fix and re-run the script (up to 3 retries).

Real-time Audit Dashboard: A built-in monitoring interface to visualize the thought process, generated code snippets, and execution outputs.

🛠️ Tech Stack
Backend: Java 25, Spring Boot 4 (Snapshot), JPA, H2 Database.

AI Framework: LangChain4j, Ollama (Llama 3.2 & DeepSeek-Coder).

Infrastructure: Docker Engine API for Java.

Frontend: HTML5, Tailwind CSS, Vanilla JavaScript.

📐 System Architecture
The request lifecycle follows a structured agentic workflow:

Intent Classification: The RouterModel analyzes the prompt to determine if it requires data analysis, code generation, or a general response.

Code Generation: For analysis tasks, the CoderModel synthesizes a precise Python script.

Sanitization: A robust parsing utility cleans the LLM output, stripping away prose and Markdown artifacts.

Sandbox Execution: The sanitized code is dispatched to an isolated Docker container.

Self-Healing Loop: If an exception occurs, the agent reflects on the error log and generates a corrected version of the code.

Async Persistence: Execution logs are saved to the H2 database using Virtual Threads to ensure zero latency for the end user.

🔧 Setup and Installation
Prerequisites
Docker Engine running locally.

Ollama installed with the following models: llama3.2:3b and llama3.1:8b.

JDK 25.

Installation Steps
Clone the repository.

Update application.yml with your Ollama base URL and model names.

Run the application using Maven:

Bash

mvn spring-boot:run
Open the Dashboard: http://localhost:8080/index.html.

🛡️ Security Implementations
This orchestrator implements critical security layers to mitigate risks associated with LLM-generated code:

Network Isolation: Containers are created with withNetworkDisabled(true) to prevent data exfiltration.

Resource Quotas: Hard limits on CPU and RAM prevent Denial of Service (DoS) attacks caused by infinite loops.

Ephemeral Lifecycle: Containers use AutoRemove and forced cleanup to ensure no residual data or processes remain post-execution.