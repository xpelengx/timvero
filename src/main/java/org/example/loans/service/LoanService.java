package org.example.loans.service;


import lombok.RequiredArgsConstructor;
import org.example.loans.dto.LoanRequest;
import org.example.loans.dto.PaymentSchedule;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    public List<PaymentSchedule> calculateLoanSchedule(LoanRequest request) {
        BigDecimal monthlyRate = request.annualInterestRate().divide(BigDecimal.valueOf(12L * 100), 10, RoundingMode.HALF_UP);
        BigDecimal annuityCoefficient = monthlyRate
                .multiply(BigDecimal.ONE.add(monthlyRate).pow(request.termMonths()))
                .divide(BigDecimal.ONE.add(monthlyRate).pow(request.termMonths()).subtract(BigDecimal.ONE), 10, RoundingMode.HALF_UP);

        BigDecimal monthlyPayment = request.loanAmount().multiply(annuityCoefficient).setScale(2, RoundingMode.HALF_UP);
        BigDecimal remainingBalance = request.loanAmount();

        List<PaymentSchedule> schedule = new ArrayList<>();
        LocalDate paymentDate = LocalDate.now().plusMonths(1);

        for (int i = 1; i <= request.termMonths(); i++) {
            BigDecimal interestPayment = remainingBalance.multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);
            BigDecimal principalPayment = monthlyPayment.subtract(interestPayment);
            remainingBalance = remainingBalance.subtract(principalPayment).setScale(2, RoundingMode.HALF_UP);

            schedule.add(new PaymentSchedule(i, paymentDate, monthlyPayment, principalPayment, interestPayment, remainingBalance));
            paymentDate = paymentDate.plusMonths(1);
        }

        return schedule;
    }

}
