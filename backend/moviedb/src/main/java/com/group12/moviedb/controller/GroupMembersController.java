package com.group12.moviedb.controller;

import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.models.GroupMembers;
import com.group12.moviedb.models.User;
import com.group12.moviedb.models.Group;
import com.group12.moviedb.repository.GroupMembersRepository;
import com.group12.moviedb.repository.GroupRepository;
import com.group12.moviedb.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class GroupMembersController {
    
    private final GroupMembersRepository groupMembersRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupMembersController (GroupMembersRepository groupMembersRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.groupMembersRepository = groupMembersRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/group/members")
    public Iterable<GroupMembers> findAllGroupMembers() {
        return this.groupMembersRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/group/members/group={group_id}")
    public List<GroupMembers> findGroupMembersByGroupId(@PathVariable int group_id) {
        Group group = groupRepository.findById(group_id)
            .orElseThrow(() -> new NoSuchElementException("Group not found"));
        if (group != null) {
            return this.groupMembersRepository.findByGroup(group);
        }
        return Collections.emptyList();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/group/members/user={user_id}")
    public List<GroupMembers> findGroupMembersByUserId(@PathVariable int user_id) {
        User user = userRepository.findById(user_id)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        if (user != null) {
            return this.groupMembersRepository.findByUser(user);
        }
        return Collections.emptyList();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/group/members/{member_id}")
    public GroupMembers findGroupMember(@PathVariable int member_id) {
        return this.groupMembersRepository.findById(member_id)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/group/members")
    public GroupMembers addOneGroupMembers(@RequestBody GroupMembers groupMembers,
                                           @RequestParam("groupId") int groupId,
                                           @RequestParam("userId") int userId) {
        Group group = groupRepository.findById(groupId)
            .orElseThrow(() -> new NoSuchElementException("Group not found"));
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        groupMembers.setJoinedAt(LocalDateTime.now());
        groupMembers.setGroup(group);
        groupMembers.setUser(user);
        return this.groupMembersRepository.save(groupMembers);
    }
    
    @CrossOrigin(origins = "*")
    @PatchMapping("/group/members/{member_id}")
    public GroupMembers updateOneGroupMembers(@PathVariable int memberId, @RequestBody Map<String, Object> updates) {
        GroupMembers groupMembers = this.groupMembersRepository.findById(memberId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));;
        if (groupMembers != null) {
        updates.forEach((key, value) -> {
            switch (key) {
                case "is_admin":
                    groupMembers.setIsAdmin((boolean) value);
                    break;
                case "joined_at":
                    groupMembers.setJoinedAt((LocalDateTime) value);
                    break;
                case "left_at":
                    groupMembers.setLeftAt((LocalDateTime) value);
                    break;
                default:
                    break;
            }
        });
        return this.groupMembersRepository.save(groupMembers);
        }
        return null;
    }   

    @DeleteMapping("/group/members/{memberId}")
    public void deleteOneGroupMembers(@PathVariable int memberId) {
        GroupMembers groupMembers = this.groupMembersRepository.findById(memberId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        this.groupMembersRepository.delete(groupMembers);
    }
    
}
