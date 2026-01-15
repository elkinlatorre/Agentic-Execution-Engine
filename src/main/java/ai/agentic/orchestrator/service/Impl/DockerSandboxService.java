package ai.agentic.orchestrator.service.Impl;

import ai.agentic.orchestrator.service.CodeExecutionService;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Frame;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class DockerSandboxService implements CodeExecutionService {

    private final DockerClient dockerClient;

    @Override
    public String executePythonCode(String code) {
        log.info("Preparing sandbox for Python execution...");

        String containerId = null;
        try {
            // 1. Create the ephemeral container
            CreateContainerResponse container = dockerClient.createContainerCmd("ai-sandbox:latest")
                    .withCmd("python3", "-c", code)
                    .withNetworkDisabled(true)
                    .exec();

            containerId = container.getId();
            log.info("Container created with ID: {}", containerId);

            // 2. Start container
            dockerClient.startContainerCmd(containerId).exec();

            // 3. Capture output
            StringBuilder output = new StringBuilder();
            LogContainerResultCallback callback = new LogContainerResultCallback() {
                @Override
                public void onNext(Frame item) {
                    output.append(new String(item.getPayload()));
                    super.onNext(item);
                }
            };

            dockerClient.logContainerCmd(containerId)
                    .withStdOut(true)
                    .withStdErr(true)
                    .withFollowStream(true)
                    .exec(callback)
                    .awaitCompletion(15, TimeUnit.SECONDS);

            return output.toString().trim();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Execution interrupted: {}", e.getMessage());
            return "Error: Execution timed out";
        } catch (Exception e) {
            log.error("Docker sandbox failure: {}", e.getMessage());
            return "Execution Error: " + e.getMessage();
        } finally {
            if (containerId != null) {
                try {
                    dockerClient.removeContainerCmd(containerId).withForce(true).exec();
                    log.info("Container {} removed successfully.", containerId);
                } catch (Exception e) {
                    log.error("Failed to remove container: {}", e.getMessage());
                }
            }
        }
    }
}