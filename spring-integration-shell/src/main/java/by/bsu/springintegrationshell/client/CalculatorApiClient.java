package by.bsu.springintegrationshell.client;

import by.bsu.springintegrationshell.dto.CalculatorApiRequest;
import by.bsu.springintegrationshell.dto.CalculateExpressionApiRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api")
public interface CalculatorApiClient {

    @PostExchange("/calculate")
    String calculate(@RequestBody CalculatorApiRequest calculatorApiRequest);

    @PostExchange("/calculateExpression")
    String calculateExpression(@RequestBody CalculateExpressionApiRequest calculateExpressionApiRequest);
}
