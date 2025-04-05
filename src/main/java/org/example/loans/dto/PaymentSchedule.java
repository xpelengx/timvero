package org.example.loans.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentSchedule(
        int month,
        LocalDate date,
        BigDecimal payment,
        BigDecimal principal,
        BigDecimal interest,
        BigDecimal remainingBalance
) {}