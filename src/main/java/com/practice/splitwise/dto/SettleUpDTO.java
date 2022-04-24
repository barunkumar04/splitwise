package com.practice.splitwise.dto;

import com.practice.splitwise.model.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class SettleUpDTO {
    private Long groupId;
    private List<Transaction> transactions = new ArrayList<>();
}
