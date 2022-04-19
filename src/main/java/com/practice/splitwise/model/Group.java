package com.practice.splitwise.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "GROUPS")
public class Group extends BaseModel{
    private String name;

    @ManyToMany
    private List<User> users;

    @ManyToMany
    private List<User> admins;

}
