package com.rohan.splitwise.models;

import java.util.List;
import java.util.Map;

public class ExpenseMapping {
    private Map<String, List<Pair>> expenseMap;

    public ExpenseMapping() {
    }

    public Map<String, List<Pair>> getExpenseMap() {
        return expenseMap;
    }

    public void setExpenseMap(Map<String, List<Pair>> expenseMap) {
        this.expenseMap = expenseMap;
    }

    public ExpenseMapping(Map<String, List<Pair>> expenseMap) {
        this.expenseMap = expenseMap;
    }
}
