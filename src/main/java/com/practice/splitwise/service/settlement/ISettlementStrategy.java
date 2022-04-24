package com.practice.splitwise.service.settlement;

import com.practice.splitwise.model.Expense;
import com.practice.splitwise.model.Transaction;

import java.util.List;

public interface ISettlementStrategy {

    List<Transaction> settleUp(List<Expense> expenses);
}
