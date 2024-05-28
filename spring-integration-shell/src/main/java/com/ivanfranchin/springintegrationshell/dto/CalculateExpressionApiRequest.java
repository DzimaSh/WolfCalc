package com.ivanfranchin.springintegrationshell.dto;

public record CalculateExpressionApiRequest(Type operation, String expression) {
}
