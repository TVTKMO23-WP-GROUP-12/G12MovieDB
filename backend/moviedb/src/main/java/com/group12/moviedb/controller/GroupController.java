package com.group12.moviedb.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group12.moviedb.models.Group;
import com.group12.moviedb.repository.GroupRepository;

@RestController
public class GroupController {

    private final GroupRepository groupRepository;

    public GroupController (GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/group")
    public Iterable<Group> findAllGroups() {
        return this.groupRepository.findAll();
    }

    @GetMapping("/group/{id}")
    public Group findOneGroup(@PathVariable int id) {
        return this.groupRepository.findById(id);
    }

    @GetMapping("/group/{group_name}")
    public Group findOneGroup(@PathVariable String groupName) {
        return this.groupRepository.findByGroupName(groupName);
    }
    
    @PostMapping("/group")
    public Group addOneGroup(@RequestBody Group group) {
        return this.groupRepository.save(group);
    }

    @PatchMapping("/group/{id}")
    public Group updateOneGroup(@PathVariable int id, @RequestBody Map<String, Object> updates) {
        Group group = this.groupRepository.findById(id);
        updates.forEach((key, value) -> {
                switch (key) {
                case "group_name":
                    group.setGroupName((String) value);
                    break;
                case "group_description":
                    group.setGroupDescription((String) value);
                    break;
                case "updated_at":
                    group.setUpdatedAt((LocalDateTime) value);
                    break;
                default:
                     break;
          }
      });
        return this.groupRepository.save(group);
    }

    @DeleteMapping("/group/{id}")
    public void deleteOneGroup(@PathVariable int id) {
        Group group = this.groupRepository.findById(id);
        this.groupRepository.delete(group);
    }

}
