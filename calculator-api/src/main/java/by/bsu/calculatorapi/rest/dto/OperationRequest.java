package by.bsu.calculatorapi.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OperationRequest(@NotNull Type operation, @NotNull BigDecimal a, @NotNull BigDecimal b){
}
