package by.bsu.calculatorapi.integration;

import by.bsu.calculatorapi.rest.dto.CalculateRequest;
import by.bsu.calculatorapi.rest.dto.OperationRequest;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.math.BigDecimal;

@MessagingGateway
public interface IntegrationGateway {

    @Gateway(requestChannel = "gatewayChannel")
    BigDecimal sendMessage(OperationRequest operationRequest);

    @Gateway(requestChannel = "gatewayChannel")
    BigDecimal sendMessage(CalculateRequest calculateRequest);
}
