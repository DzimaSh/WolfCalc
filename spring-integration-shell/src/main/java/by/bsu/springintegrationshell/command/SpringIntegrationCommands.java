package by.bsu.springintegrationshell.command;

import by.bsu.springintegrationshell.dto.CalculatorApiRequest;
import by.bsu.springintegrationshell.integration.IntegrationGateway;
import by.bsu.springintegrationshell.dto.CalculateExpressionApiRequest;
import by.bsu.springintegrationshell.dto.FileInfoRequest;
import by.bsu.springintegrationshell.dto.GreetingRequest;
import by.bsu.springintegrationshell.dto.Type;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.math.BigDecimal;
import java.nio.file.Paths;

@ShellComponent
public class SpringIntegrationCommands {

    private final IntegrationGateway integrationGateway;

    public SpringIntegrationCommands(IntegrationGateway integrationGateway) {
        this.integrationGateway = integrationGateway;
    }

    @Value("${application.outbound.path}")
    private String outboundPath;


    @ShellMethod("Add two numbers")
    public String add(BigDecimal a, BigDecimal b) {
        return integrationGateway.sendMessage(new CalculatorApiRequest(Type.ADD, a, b));
    }

    @ShellMethod("Subtract two numbers")
    public String subtract(BigDecimal a, BigDecimal b) {
        return integrationGateway.sendMessage(new CalculatorApiRequest(Type.SUBTRACT, a, b));
    }

    @ShellMethod("Divide two numbers")
    public String divide(BigDecimal a, BigDecimal b) {
        return integrationGateway.sendMessage(new CalculatorApiRequest(Type.DIVIDE, a, b));
    }

    @ShellMethod("Multiply two numbers")
    public String multiply(BigDecimal a, BigDecimal b) {
        return integrationGateway.sendMessage(new CalculatorApiRequest(Type.MULTIPLY, a, b));
    }
    
    @ShellMethod("Calculate expression")
    public String calculate(String expression) {
        return integrationGateway.sendMessage(new CalculateExpressionApiRequest(Type.CALCULATE, expression));
    }

    @ShellMethod("Greet someone")
    public String greet(@ShellOption(defaultValue = "World") String name) {
        return integrationGateway.sendMessage(new GreetingRequest(name));
    }

    @ShellMethod("Get info from file")
    public String getInfoFromFile(String filename) {
        return integrationGateway.sendMessage(new FileInfoRequest(filename));
    }

    @ShellMethod("Write to file")
    public void writeToFile(String filename, String content) {
        integrationGateway.writeToFile(filename, Paths.get(outboundPath).toFile(), content);
    }
}
