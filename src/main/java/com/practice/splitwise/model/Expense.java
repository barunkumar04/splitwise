package com.practice.splitwise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "EXPENSES")
public class Expense extends BaseModel {
    private String description;
    private int amount; // In paisa, to avoid precision issues

    @ManyToOne
    private User createdBy;

    @ElementCollection // Allows to store map flatten and stored as table in datastore
    private Map<User, Integer> paidBy;

    @ElementCollection
    private Map<User, Integer> owedBy;
}
