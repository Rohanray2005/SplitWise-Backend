package com.rohan.splitwise.service;

import com.rohan.splitwise.models.Expense;
import com.rohan.splitwise.models.ExpenseMapping;
import com.rohan.splitwise.models.Pair;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public ExpenseMapping getExpenseMappingOutOfGroupExpenseMap(Map<String, Double> groupExpenseMap) {
        Map<String, List<Pair>> expenseMap = new HashMap<>();

        PriorityQueue<Pair> minHeap = new PriorityQueue<>(
                Comparator.comparingDouble(Pair::getAmount)
        );

        PriorityQueue<Pair> maxHeap = new PriorityQueue<>(
                Comparator.comparingDouble(Pair::getAmount).reversed()
        );

        groupExpenseMap.forEach((userId, amount) -> {
                    if (amount > 0) {
                        maxHeap.add(new Pair(userId, amount));
                    } else {
                        minHeap.add(new Pair(userId, amount));
                    }
                }
        );

        // todo: handle case of amounts being equal and cancel them first
        // todo: optimize code

        Pair obj1 = maxHeap.poll();
        Pair obj2 = minHeap.poll();

        do {
            if (obj1.getAmount() == Math.abs(obj2.getAmount())) {
                expenseMap
                        .computeIfAbsent(obj2.getUserId(), k -> new ArrayList<>())
                        .add(new Pair(obj1.getUserId(), obj1.getAmount()));
                obj1 = maxHeap.poll();
                obj2 = minHeap.poll();
            } else if(obj1.getAmount() > Math.abs(obj2.getAmount())) {
                expenseMap
                        .computeIfAbsent(obj2.getUserId(), k -> new ArrayList<>())
                        .add(new Pair(obj1.getUserId(), obj1.getAmount()));
                obj1.setAmount(obj1.getAmount() - Math.abs(obj2.getAmount()));
                obj2 = minHeap.poll();
            } else {
                expenseMap
                        .computeIfAbsent(obj2.getUserId(), k -> new ArrayList<>())
                        .add(new Pair(obj1.getUserId(), obj1.getAmount()));
                obj1 = maxHeap.poll();
                obj2.setAmount(obj2.getAmount() + obj1.getAmount());
            }
        } while(!maxHeap.isEmpty() && !minHeap.isEmpty());

        return new ExpenseMapping(expenseMap);
    }


}
