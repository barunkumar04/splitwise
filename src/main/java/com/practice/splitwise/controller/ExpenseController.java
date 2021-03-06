package com.practice.splitwise.controller;

import com.practice.splitwise.dto.ResponseDTO;
import com.practice.splitwise.dto.CreateExpenseDTO;
import com.practice.splitwise.exceptons.InvalidUserException;
import com.practice.splitwise.model.Expense;
import com.practice.splitwise.dto.ExpenseDTO;
import com.practice.splitwise.service.ExpenseService;
import com.practice.splitwise.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/expense")
    public ResponseDTO<ExpenseDTO> createExpense(@RequestBody CreateExpenseDTO expenseRequest) {
        Optional<ExpenseDTO> expense;
        try{
            expense = expenseService.createUserExpense(expenseRequest.getUserId(), expenseRequest);
        }catch (InvalidUserException ex){
            return ResponseDTO.notFound();
        }

        return ResponseUtils.toResponse(expense);
    }

    @GetMapping("/expense/{expenseId}")
    public ResponseDTO<ExpenseDTO> getExpense(@PathVariable Long expenseId) {
        Optional<Expense> expense = expenseService.getExpense(expenseId);
        return ResponseUtils.toResponse(expense.map(ExpenseDTO::from));
    }
}