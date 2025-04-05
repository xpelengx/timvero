package org.example.loans.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.loans.dto.LoanRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoanControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void calculateLoan_shouldReturn200AndSchedule() throws Exception {
        LoanRequest request = new LoanRequest(
                new BigDecimal("100000"),
                new BigDecimal("10"),
                12
        );

        mockMvc.perform(post("/api/loans/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(12));
    }

    @Test
    void calculateLoan_shouldReturn400WhenInvalidInput() throws Exception {
        LoanRequest request = new LoanRequest(
                new BigDecimal("0"),
                null,
                -1
        );

        mockMvc.perform(post("/api/loans/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.loanAmount").exists())
                .andExpect(jsonPath("$.annualInterestRate").exists())
                .andExpect(jsonPath("$.termMonths").exists());
    }
}