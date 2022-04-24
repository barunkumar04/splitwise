package com.practice.splitwise.service.settlement;

import com.practice.splitwise.model.Expense;
import com.practice.splitwise.model.Transaction;

import java.util.List;

public class GreedySettlementStrategy implements ISettlementStrategy{

    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        return null;
    }
}
