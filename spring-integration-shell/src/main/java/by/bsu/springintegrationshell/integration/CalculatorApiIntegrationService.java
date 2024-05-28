package by.bsu.springintegrationshell.integration;

import by.bsu.springintegrationshell.client.CalculatorApiClient;
import by.bsu.springintegrationshell.dto.CalculateExpressionApiRequest;
import by.bsu.springintegrationshell.dto.CalculatorApiRequest;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CalculatorApiIntegrationService {

    private final CalculatorApiClient calculatorApiClient;

    public CalculatorApiIntegrationService(CalculatorApiClient calculatorApiClient) {
        this.calculatorApiClient = calculatorApiClient;
    }

    @ServiceActivator(inputChannel = "calculatorRouterChannel")
    public String calculatorHandler(@Payload CalculatorApiRequest calculatorApiRequest) {
        return calculatorApiClient.calculate(calculatorApiRequest);
    }

    @ServiceActivator(inputChannel = "calculatorRouterChannel")
    public String calculatorHandler(@Payload CalculateExpressionApiRequest calculateExpressionApiRequest) {
        return calculatorApiClient.calculateExpression(calculateExpressionApiRequest);
    }
}
