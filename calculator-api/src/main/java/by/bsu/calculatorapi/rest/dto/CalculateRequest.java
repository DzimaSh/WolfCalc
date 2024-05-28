package by.bsu.calculatorapi.rest.dto;

import jakarta.validation.constraints.NotNull;

public record CalculateRequest(@NotNull Type operation, @NotNull String expression) {
}
