package com.practice.splitwise.dto;

import com.practice.splitwise.constants.Currency;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CreateExpenseDTO {
    private long userId;
    private String description;
    private long amount;
    private Currency currency;
    private Map<Long, Long> paidBy = new HashMap<>();
    private Map<Long, Long> owedBy = new HashMap<>();
}