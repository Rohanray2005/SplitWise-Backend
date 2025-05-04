package com.rohan.splitwise.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "expense")
public class ExpenseEntity {

    @Id
    @Column
    private String expenseId;

    @Column
    private String groupId;

    @Column
    private String userId;


}
