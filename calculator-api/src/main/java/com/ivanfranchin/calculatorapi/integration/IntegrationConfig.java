package com.ivanfranchin.calculatorapi.integration;

import com.ivanfranchin.calculatorapi.rest.dto.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.ExpressionEvaluatingRouter;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Bean
    public MessageChannel gatewayChannel() {
        return new PublishSubscribeChannel();
    }

    @Router(inputChannel = "gatewayChannel")
    @Bean
    public ExpressionEvaluatingRouter expressionEvaluatingRouter() {
        ExpressionEvaluatingRouter router = new ExpressionEvaluatingRouter("payload.operation");
        router.setChannelMapping(Type.ADD.name(), "addRouterChannel");
        router.setChannelMapping(Type.SUBTRACT.name(), "subtractRouterChannel");
        router.setChannelMapping(Type.DIVIDE.name(), "divideRouterChannel");
        router.setChannelMapping(Type.MULTIPLY.name(), "multiplyRouterChannel");
        router.setChannelMapping(Type.CALCULATE.name(), "calculateRouterChannel");
        return router;
    }
}
