package com.practice.splitwise.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@MappedSuperclass // To create an entity super class
@EntityListeners(AuditingEntityListener.class) // To create audit fields like created at, modified at etc
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
}
