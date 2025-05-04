package com.rohan.splitwise.service;

import com.rohan.splitwise.models.Expense;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ExpenseService {


    public void findExpenseSplit(Expense expense, List<String> userIds, Map<String, Double>expenseMap) {
        Double amountToBePaidByEachUser = expense.getAmount()/(userIds.size());

        for(String userId : userIds) {
            if(Objects.equals(userId, expense.getUserId())) {
                expenseMap.put(userId,
                        expenseMap.getOrDefault(userId,0.0)
                                + expense.getAmount() - amountToBePaidByEachUser);
            }else {
                expenseMap.put(userId,
                        expenseMap.getOrDefault(userId,0.0)
                                - amountToBePaidByEachUser);
            }
        }

    }


}
