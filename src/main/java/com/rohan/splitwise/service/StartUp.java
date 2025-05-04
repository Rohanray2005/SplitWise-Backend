package com.rohan.splitwise.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rohan.splitwise.models.Group;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class StartUp {

    private final GroupService groupService;

    public StartUp(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostConstruct
    public void loadGroup() throws IOException {
        File file = new File("response1.json");
        InputStream is = new FileInputStream(file);

        ObjectMapper objectMapper = new ObjectMapper();

        Group group =  objectMapper.readValue(is, new TypeReference<Group>() {});
        groupService.createGroup(group);
    }
}
