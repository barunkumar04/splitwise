package com.practice.splitwise.controller;

import com.practice.splitwise.dto.CreateGroupDTO;
import com.practice.splitwise.dto.GroupDTO;
import com.practice.splitwise.dto.ResponseDTO;
import com.practice.splitwise.dto.SettleUpDTO;
import com.practice.splitwise.exceptons.InvalidGroupException;
import com.practice.splitwise.model.Group;
import com.practice.splitwise.service.GroupService;
import com.practice.splitwise.utils.ResponseUtils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/group")
    public ResponseDTO<GroupDTO> createGroup(@RequestBody CreateGroupDTO createGroupRequest) {
        GroupDTO group = groupService.createGroup(createGroupRequest);
        return ResponseUtils.toResponse(Optional.of(group));
    }

    @GetMapping("/group/{groupId}")
    public ResponseDTO<GroupDTO> getGroup(@PathVariable Long groupId) {
        Optional<Group> expense = groupService.getGroup(groupId);
        return ResponseUtils.toResponse(expense.map(GroupDTO::from));
    }

    @PostMapping("/group/{groupId}/settleUp")
    public ResponseDTO<SettleUpDTO> settleUp(@PathVariable Long groupId) throws InvalidGroupException {
        SettleUpDTO transactions = groupService.settleUp(groupId);
        return ResponseDTO.success(transactions);
    }

}

