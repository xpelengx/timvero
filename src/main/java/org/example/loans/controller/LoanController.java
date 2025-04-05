package org.example.loans.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.loans.dto.LoanRequest;
import org.example.loans.dto.PaymentSchedule;
import org.example.loans.service.LoanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
@Tag(name = "Loan Calculator", description = "API for calculating loan payment schedules using annuity payments")
public class LoanController {

    private final LoanService loanService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Loan schedule calculated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/calculate")
    public List<PaymentSchedule> calculateLoan(@Valid @RequestBody LoanRequest request) {
        return loanService.calculateLoanSchedule(request);
    }
}