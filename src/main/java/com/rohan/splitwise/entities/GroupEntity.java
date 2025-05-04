package com.rohan.splitwise.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "group")
public class GroupEntity {

    @Id
    @Column
    private String groupId;

    @Column
    private String expenseId;

    @Column
    private String userId;

}
