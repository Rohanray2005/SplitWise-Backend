package com.rohan.splitwise.models;

import lombok.Data;

import java.util.*;

@Data
public class Group {

    private String groupId;

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    private List<Expense> expenses;

    public Map<String, Double> getGroupExpenseMap() {
        return groupExpenseMap;
    }

    public void setGroupExpenseMap(Map<String, Double> groupExpenseMap) {
        this.groupExpenseMap = groupExpenseMap;
    }

    private Set<User> users;

    private Map<String, Double> groupExpenseMap;

    public Group() {
        groupId = UUID.randomUUID().toString();
        this.users = new HashSet<>();
        this.expenses = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    public String getGroupId() {
        return groupId;
    }
}
