package com.group12.moviedb.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.models.Group;
import com.group12.moviedb.models.User;
import com.group12.moviedb.repository.GroupRepository;
import com.group12.moviedb.repository.UserRepository;

@RestController
public class GroupController {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupController (GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/group")
    public Iterable<Group> findAllGroups() {
        return this.groupRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/group/{id}")
    public Group findOneGroup(@PathVariable int id) {
        return this.groupRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Group not found"));
    }
    
    @PostMapping("/group")
    public Group addOneGroup(@RequestBody Group group, @RequestParam("userId") int userId) {
        group.setCreatedAt(LocalDateTime.now());
        group.setUpdatedAt(LocalDateTime.now());
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        group.setUserId(user); 
        group.setGroupName(group.getGroupName());
        group.setGroupDescription(group.getGroupDescription());
        return groupRepository.save(group);
    }
    
    
    @PatchMapping("/group/{id}")
    public Group updateOneGroup(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        Group group = this.groupRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Group not found"));
        updates.forEach((key, value) -> {
                switch (key) {
                case "groupName":
                    group.setGroupName((String) value);
                    break;
                case "groupDescription":
                    group.setGroupDescription((String) value);
                    break;
                case "updatedAt":
                    group.setUpdatedAt((LocalDateTime) value);
                    break;
                default:
                     break;
          }
      });
        group.setUpdatedAt(LocalDateTime.now());
        return this.groupRepository.save(group);
    }

    @DeleteMapping("/group/{id}")
    public void deleteOneGroup(@PathVariable int id) {
        Group group = this.groupRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Group not found"));
        this.groupRepository.delete(group);
    }

}
