package com.practice.splitwise.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private Long from;
    private Long to;
    private Long amount;
}
