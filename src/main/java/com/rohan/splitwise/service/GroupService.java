package com.rohan.splitwise.service;

import com.rohan.splitwise.models.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class GroupService {

    private Map<String, Group> groupMap;

    private UserService userService;

    private ExpenseService expenseService;

    public GroupService(UserService userService, ExpenseService expenseService) {
        this.userService = userService;
        this.expenseService = expenseService;
        groupMap = new HashMap<>();
    }

    public Group createGroup() {
        Group newGroup = new Group();
        groupMap.put(newGroup.getGroupId(), newGroup);
        return newGroup;
    }

    public Group createGroup(Group group) {
        groupMap.put(group.getGroupId(), group);
        return groupMap.get(group.getGroupId());
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
        expense.setExpenseStatus(ExpenseStatus.CREATED);
        expense.setExpenseDate(LocalDate.now().toString());
        group.addExpense(expense);
        List<String> userIds = group.getUsers().stream().map(User::getUserId).toList();
        expenseService.findExpenseSplit(expense, userIds, group.getGroupExpenseMap());
        groupMap.put(groupId, group);
        return group;
    }

    public Group getGroupDetails(String groupId) {
        return groupMap.get(groupId);
    }

    public ExpenseMapping getExpenseMap(String groupId) {
        Group group = groupMap.get(groupId);
        return expenseService.getExpenseMappingOutOfGroupExpenseMap(group.getGroupExpenseMap());
    }

}
