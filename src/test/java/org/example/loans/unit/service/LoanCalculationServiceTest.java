package org.example.loans.unit.service;

import org.example.loans.dto.PaymentSchedule;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import org.example.loans.dto.LoanRequest;
import org.example.loans.service.LoanService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.withinPercentage;

class LoanCalculationServiceTest {

    private final LoanService loanCalculationService = new LoanService();

    @Test
    void calculateLoanSchedule_shouldReturnCorrectSize() {
        LoanRequest request = new LoanRequest(
                new BigDecimal("100000"),
                new BigDecimal("10"),
                12
        );

        List<PaymentSchedule> schedule = loanCalculationService.calculateLoanSchedule(request);

        assertThat(schedule).hasSize(12);
    }

    @Test
    void calculateLoanSchedule_firstMonthPaymentShouldBeCorrect() {
        LoanRequest request = new LoanRequest(
                new BigDecimal("120000"),
                new BigDecimal("12"),
                12
        );

        List<PaymentSchedule> schedule = loanCalculationService.calculateLoanSchedule(request);

        PaymentSchedule firstMonth = schedule.get(0);

        assertThat(firstMonth.payment()).isCloseTo(new BigDecimal("10666.67"), withinPercentage(1.0));
    }
}