package com.rohan.splitwise.controller;

import com.rohan.splitwise.models.Expense;
import com.rohan.splitwise.models.ExpenseMapping;
import com.rohan.splitwise.models.Group;
import com.rohan.splitwise.models.User;
import com.rohan.splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class groupcontroller {

    @Autowired
    private GroupService groupService;

    @GetMapping("/group-details/{groupId}")
    public ResponseEntity<Group> getGroupDetails(@PathVariable String groupId) {
        return ResponseEntity.ok(groupService.getGroupDetails(groupId));
    }

    @PostMapping("/create-group")
    public Group createNewGroup() {
        return groupService.createGroup();
    }

    @PostMapping("/add-user-to-group/{groupId}/user/{userId}")
    public Group addUserToGroup(@PathVariable String groupId, @PathVariable String userId) {
        return groupService.addUser(groupId, userId);
    }

    @PostMapping("/add-expense-to-group/{groupId}")
    public Group addExpenseToGroup(@PathVariable String groupId, @RequestBody Expense expense) {
        return groupService.addExpense(groupId, expense);
    }

    @GetMapping("/get-expense-details/{groupId}")
    public ExpenseMapping getExpenseDetails(@PathVariable String groupId) {
        return groupService.getExpenseMap(groupId);
    }

}
