package by.bsu.springintegrationshell.integration;

import by.bsu.springintegrationshell.client.FileServiceClient;
import by.bsu.springintegrationshell.dto.FileInfoRequest;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class FileServiceIntegrationService {

    private final FileServiceClient fileServiceClient;

    public FileServiceIntegrationService(FileServiceClient fileServiceClient) {
        this.fileServiceClient = fileServiceClient;
    }

    @ServiceActivator(inputChannel = "fileInfoRouterChannel")
    public String fileInfoHandler(@Payload FileInfoRequest fileInfoRequest) {
        return fileServiceClient.getFile(fileInfoRequest.filename());
    }
}
