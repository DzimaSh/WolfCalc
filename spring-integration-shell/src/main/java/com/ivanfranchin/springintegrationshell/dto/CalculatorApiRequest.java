package com.ivanfranchin.springintegrationshell.dto;

import java.math.BigDecimal;

public record CalculatorApiRequest(Type operation, BigDecimal a, BigDecimal b) {
}
