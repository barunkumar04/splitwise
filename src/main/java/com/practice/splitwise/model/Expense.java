package com.practice.splitwise.model;

import com.practice.splitwise.constants.Currency;
import com.practice.splitwise.dto.CreateExpenseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "EXPENSES")
public class Expense extends BaseModel {
    @ElementCollection
    Map<User, Long> paidBy = new HashMap<>();
    @ElementCollection
    Map<User, Long> owedBy = new HashMap<>();
    @ManyToOne
    private User createdBy;
    private long amount;
    private Currency currency;

    public static Expense from(CreateExpenseDTO expenseRequest, Map<User, Long> paidBy, Map<User, Long> owedBy) {
        return null;
    }
}
