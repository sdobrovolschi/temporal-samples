package com.example.transfer.lang;

import java.math.BigDecimal;
import java.util.Currency;

public record Money(BigDecimal value, Currency currency) {
}
