package com.practice.splitwise.service;

import com.practice.splitwise.dto.CreateExpenseDTO;
import com.practice.splitwise.exceptons.InvalidUserException;
import com.practice.splitwise.model.Expense;
import com.practice.splitwise.dto.ExpenseDTO;
import com.practice.splitwise.model.User;
import com.practice.splitwise.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    public Optional<ExpenseDTO> createUserExpense(Long userId, CreateExpenseDTO expenseRequest) throws InvalidUserException {

        // Check if user exists
        Optional<User> createdBy = userService.getUser(userId);
        if (!createdBy.isPresent()) {
            throw new InvalidUserException("User is not registered");
        }

        // Validate owedBy and paidBy
        Set<Long> userIds = Stream.of(expenseRequest.getPaidBy().keySet(), expenseRequest.getOwedBy().keySet())
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        boolean aresUsersValid = userService.validateIds(userIds);
        if (!aresUsersValid) {
            return Optional.empty();
        }

        // Create expense
        Expense expense = transformExpenseRequest(expenseRequest);

        // Persist expense
        Expense persistedExpense = expenseRepository.save(expense);
        return Optional.of(ExpenseDTO.from(persistedExpense));
    }

    private Expense transformExpenseRequest(CreateExpenseDTO expenseRequest) {
        Map<User, Long> paidBy = transformReferencedUsers(expenseRequest.getPaidBy());
        Map<User, Long> owedBy = transformReferencedUsers(expenseRequest.getOwedBy());
        return Expense.from(expenseRequest, paidBy, owedBy);
    }

    public Optional<Expense> getExpense(Long expenseId) {
        return expenseRepository.findById(expenseId);
    }

    private Map<User, Long> transformReferencedUsers(Map<Long, Long> source) {
        Map<User, Long> target = new HashMap<>();
        for (Map.Entry<Long, Long> id_amount : source.entrySet()) {
            Long id = id_amount.getKey();
            Long amount = id_amount.getValue();

            Optional<User> user = userService.getUser(id);
            target.put(user.get(), amount);
        }
        return target;
    }
    
}