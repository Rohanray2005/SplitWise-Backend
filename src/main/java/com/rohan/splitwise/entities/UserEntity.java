package com.rohan.splitwise.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column
    private String userId;

    @Column
    private String userName;

    @Column
    private String userEmail;

    @Column
    private Double netBalance;

    @PrePersist
    public void generateId() {
        if (userId == null) {
            userId = UUID.randomUUID().toString();
        }
    }
}
