package com.practice.splitwise.service;

import com.practice.splitwise.dto.CreateGroupDTO;
import com.practice.splitwise.dto.GroupDTO;
import com.practice.splitwise.dto.SettleUpDTO;
import com.practice.splitwise.exceptons.InvalidGroupException;
import com.practice.splitwise.exceptons.InvalidUserException;
import com.practice.splitwise.model.Expense;
import com.practice.splitwise.model.Group;
import com.practice.splitwise.model.Transaction;
import com.practice.splitwise.repository.GroupRepository;
import com.practice.splitwise.service.settlement.GreedySettlementStrategy;
import com.practice.splitwise.service.settlement.ISettlementStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    private ISettlementStrategy settlementStrategy = new GreedySettlementStrategy();

    public GroupDTO createGroup(CreateGroupDTO createGroupRequest) {
        Group group = groupRepository.save(Group.from(createGroupRequest));
        return GroupDTO.from(group);
    }

    public Optional<Group> getGroup(Long groupId) {
        return groupRepository.findById(groupId);
    }

    public SettleUpDTO settleUp(Long groupId) throws InvalidGroupException {
        //Check if group is valid
        if(!groupRepository.existsById(groupId)){
            throw new InvalidGroupException("Group not found.");
        }

        //Fetch expenses
        Group group = getGroup(groupId).get();
        List<Expense> groupExpenses = group.getExpenses();

        List<Transaction> transactions = settlementStrategy.settleUp(groupExpenses);

        // Transformation
        return SettleUpDTO.builder().groupId(groupId).transactions(transactions).build();

    }
}