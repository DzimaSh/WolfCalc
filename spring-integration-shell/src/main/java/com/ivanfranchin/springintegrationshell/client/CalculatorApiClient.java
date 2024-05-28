package com.ivanfranchin.springintegrationshell.client;

import com.ivanfranchin.springintegrationshell.dto.CalculateExpressionApiRequest;
import com.ivanfranchin.springintegrationshell.dto.CalculatorApiRequest;
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
