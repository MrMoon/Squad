package com.squad.group.controller;

import com.squad.group.model.Group;
import com.squad.group.model.Role;
import com.squad.group.model.User;
import com.squad.group.model.UserRole;
import com.squad.group.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{groupId}")
    public ResponseEntity<String> getGroupById(@PathVariable("groupId") String groupId) {
        return this.groupService.findById(Long.valueOf(groupId)).map(value -> ResponseEntity.ok(value.toString())).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createGroup(@RequestBody Group group) {
        return ResponseEntity.ok(this.groupService.create(group).toString());
    }

    @PutMapping("/{parentId}/{subId}")
    public ResponseEntity<String> relation(@PathVariable("parentId") String parentId , @PathVariable("subId") String subId) {
        Optional<Group> parent = this.groupService.findById(Long.valueOf(parentId));
        if (parent.isEmpty()) return ResponseEntity.notFound().build();
        Optional<Group> child = this.groupService.findById(Long.valueOf(subId));
        if(child.isEmpty()) return ResponseEntity.notFound().build();
        Role role = new Role();
        role.setMainGroup(parent.get());
        role.setSubGroup(child.get());
        return ResponseEntity.ok("Done");
    }

    @PutMapping("/{groupId}/{userId}")
    public ResponseEntity<String> squidRelation(@PathVariable("groupId") String groupId , @PathVariable("userId") String userId) {
        Optional<Group> group = this.groupService.findById(Long.valueOf(groupId));
        if(group.isEmpty()) return ResponseEntity.notFound().build();
        UserRole userRole = new UserRole();
        User user = new User();
        user.setUserId(userId);
        userRole.setSquad(group.get());
        userRole.setUser(user);
        return ResponseEntity.ok("Done");
    }
}
