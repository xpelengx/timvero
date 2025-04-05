package org.example.loans.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LoanRequest(

        @NotNull(message = "{loan.amount.notnull}")
        @DecimalMin(value = "100", message = "{loan.amount.min}")
        @DecimalMax(value = "1000000000.00", message = "{loan.amount.max}")
        BigDecimal loanAmount,

        @NotNull(message = "{loan.rate.notnull}")
        @DecimalMin(value = "0.1", inclusive = true, message = "{loan.rate.min}")
        @DecimalMax("100.00")
        BigDecimal annualInterestRate,

        @Max(value = 1200, message = "{loan.term.max}")
        @Min(value = 1, message = "{loan.term.min}")
        int termMonths
) {
}
