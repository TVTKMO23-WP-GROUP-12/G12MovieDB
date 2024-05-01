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
import java.util.Optional;

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
    @GetMapping("/group/members/{group_id}")
    public List<GroupMembers> findGroupMembersByGroupId(@PathVariable("group_id") int groupId) {
        Group group = groupRepository.findById(groupId)
            .orElseThrow(() -> new NoSuchElementException("Group not found"));
        if (group != null) {
            return this.groupMembersRepository.findByGroup(group);
        }
        return Collections.emptyList();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/group/members/user/{user_id}")
    public List<GroupMembers> findGroupMembersByUserId(@PathVariable("user_id") Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return this.groupMembersRepository.findByUser(userOptional.get());
        }
        return Collections.emptyList();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/group/members/member/{member_id}")
    public GroupMembers findGroupMember(@PathVariable Integer memberId) {
        return this.groupMembersRepository.findById(memberId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/group/members")
    public GroupMembers addOneGroupMembers(@RequestBody GroupMembers groupMembers,
                                           @RequestParam("memberId") Integer memberId,
                                           @RequestParam("userId") Integer userId) {
        Group group = groupRepository.findById(memberId)
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
    public GroupMembers updateOneGroupMembers(@PathVariable Integer memberId, @RequestBody Map<String, Object> updates) {
        GroupMembers groupMembers = this.groupMembersRepository.findById(memberId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
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
    
            // Save and update
            return this.groupMembersRepository.save(groupMembers);
        }
        
        // if group member is not found (throw an exception maybe...)
        return null;
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/group/members/{group_id}")
    public void deleteOneGroupMembers(@PathVariable Integer groupId) {
        GroupMembers groupMembers = this.groupMembersRepository.findById(groupId)
            .orElseThrow(() -> new NoSuchElementException("Group member not found"));
        // Delete group member->
        this.groupMembersRepository.delete(groupMembers);
    }
}
