package by.bsu.calculatorapi.rest;

import by.bsu.calculatorapi.rest.dto.CalculateRequest;
import by.bsu.calculatorapi.integration.IntegrationGateway;
import by.bsu.calculatorapi.rest.dto.OperationRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    private final IntegrationGateway integrationGateway;

    public CalculatorController(IntegrationGateway integrationGateway) {
        this.integrationGateway = integrationGateway;
    }

    @PostMapping("/calculate")
    public BigDecimal calculate(@Valid @RequestBody OperationRequest operationRequest) {
        return integrationGateway.sendMessage(operationRequest);
    }

    @PostMapping("/calculateExpression")
    public BigDecimal calculateExpression(@Valid @RequestBody CalculateRequest calculateRequest) {
        return integrationGateway.sendMessage(calculateRequest);
    }
}
