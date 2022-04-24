package com.practice.splitwise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

import com.practice.splitwise.dto.CreateGroupDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "GROUPS")
public class Group extends BaseModel {
    private String name;

    @ManyToOne
    private User admin;

    @ManyToMany
    private List<User> members = new ArrayList<>();

    @OneToMany
    private List<Expense> expenses = new ArrayList<>();

    public static Group from(CreateGroupDTO createGroupRequest) {
        return null;
    }
}
