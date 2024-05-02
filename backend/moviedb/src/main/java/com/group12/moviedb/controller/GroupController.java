package com.group12.moviedb.controller;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    @GetMapping("/group/{group_id}")
    public ResponseEntity<Group> findOneGroup(@PathVariable("group_id") Integer groupId) {
        Optional<Group> group = this.groupRepository.findById(groupId);
        if (group.isPresent()) {
            return new ResponseEntity<>(group.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping("/group")
    public Group addOneGroup(@ModelAttribute Group group, @RequestParam("userId") Integer userId) {
        group.setCreatedAt(LocalDateTime.now());
        group.setUpdatedAt(LocalDateTime.now());
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User not found"));
        group.setUser(user); 
        group.setGroupName(group.getGroupName());
        group.setGroupDescription(group.getGroupDescription());
        return groupRepository.save(group);
    }
    
    @CrossOrigin(origins = "*")
    @PatchMapping("/group/{group_id}")
    public Group updateOneGroup(@PathVariable("group_id") Integer groupId, @RequestBody Map<String, Object> updates) {
        Group group = this.groupRepository.findById(groupId).orElse(null);
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


    @CrossOrigin(origins = "*")
    @DeleteMapping("group/{group_id}")
    public void deleteOneGroup(@PathVariable Integer groupId) {
        Group group = this.groupRepository.findById(groupId).orElse(null);
        this.groupRepository.delete(group);
    }

}