package com.squad.group.service;

import com.squad.group.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    Group create(Group group);
    void update(Group group);
    Optional<Group> findById(Long groupId);
    boolean delete(Long groupId);
    List<Group> getGroupsWhoParents();

}
