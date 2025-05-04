package com.rohan.splitwise.models;

import lombok.Data;

@Data
public class Expense {

    private String expenseId;

    private String description;

    private String userId;

    private Double amount;

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Expense(String expenseId, String description, String userId, Double amount) {
        this.expenseId = expenseId;
        this.description = description;
        this.userId = userId;
        this.amount = amount;
    }
}
