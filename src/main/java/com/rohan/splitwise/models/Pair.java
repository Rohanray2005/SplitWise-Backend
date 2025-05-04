package com.rohan.splitwise.models;

public class Pair {
    private String userId;

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

    public Pair(String userId, Double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    private Double amount;
}
