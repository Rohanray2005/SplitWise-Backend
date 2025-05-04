package com.rohan.splitwise.service;

import com.rohan.splitwise.models.Expense;
import com.rohan.splitwise.models.Group;
import com.rohan.splitwise.models.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class GroupService {

    private Map<String, Group> groupMap;

    private UserService userService;

    private ExpenseService expenseService;

    private Map<String, Double> expenseMap;

    public GroupService(UserService userService, ExpenseService expenseService) {
        this.userService = userService;
        this.expenseService = expenseService;
        groupMap = new HashMap<>();
        expenseMap = new HashMap<>();
    }

    public Group createGroup() {
        Group newGroup = new Group();
        groupMap.put(newGroup.getGroupId(), newGroup);
        return newGroup;
    }

    public Group addUser(String groupId, String userId) {
        User user = userService.getUser(userId);
        Group newGroup = groupMap.get(groupId);
        newGroup.addUser(user);
        groupMap.put(groupId, newGroup);
        return newGroup;
    }

    public Group addExpense(String groupId, Expense expense){
        Group group = groupMap.get(groupId);
        expense.setExpenseId(UUID.randomUUID().toString());
        group.addExpense(expense);
        List<String> userIds = group.getUsers().stream().map(User::getUserId).toList();
        expenseService.findExpenseSplit(expense, userIds, expenseMap);
        group.setGroupExpenseMap(expenseMap);
        groupMap.put(groupId, group);
        return group;
    }

    public Group getGroupDetails(String groupId) {
        return groupMap.get(groupId);
    }

}
