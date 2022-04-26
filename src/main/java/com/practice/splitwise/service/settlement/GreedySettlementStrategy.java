package com.practice.splitwise.service.settlement;

import com.practice.splitwise.model.Expense;
import com.practice.splitwise.model.Transaction;
import com.practice.splitwise.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreedySettlementStrategy implements ISettlementStrategy{

    @Override
    public List<Transaction> settleUp(List<Expense> expenses) {
        // Compute deltas

        Map<Long, Long> deltas = computeDeltas(expenses);
        return null;
    }

    private Map<Long, Long> computeDeltas(List<Expense> expenses) {
        Map<Long, Long> deltas = new HashMap<>();
        for(Expense expense : expenses){
            for(User user : expense.getOwedBy().keySet()){
                if(!deltas.containsKey(expense.getId())){
                    deltas.put(user.getId(), 0l);
                }else{
                    deltas.put(user.getId(), deltas.get(user.getId()) + expense.getOwedBy().get(expense.getId()));
                }
            }

            for(User user : expense.getPaidBy().keySet()){
                if(!deltas.containsKey(expense.getId())){
                    deltas.put(user.getId(), 0l);
                }else{
                    deltas.put(user.getId(), deltas.get(user.getId()) - expense.getPaidBy().get(expense.getId()));
                }
            }


        }
        return deltas;
    }
}
