package com.squad.group.service;

import com.squad.group.model.Group;
import com.squad.group.producer.GroupEventProducer;
import com.squad.group.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupEventProducer groupEventProducer;


    @Override
    public Group create(Group group) {
        group.setGroupId(UUID.randomUUID().toString());
        this.groupEventProducer.produceGroupEvent(group.getGroupId() , group);
        return this.groupRepository.save(group);
    }

    @Override
    public void update(Group group) {
        this.groupEventProducer.produceGroupEvent(group.getGroupId() , group);
        this.groupRepository.save(group);
    }

    @Override
    public Optional<Group> findById(Long groupId) {
        return this.groupRepository.findById(groupId);
    }

    @Override
    public boolean delete(Long groupId) {
        this.groupEventProducer.produceGroupEvent(groupId.toString() , null);
        Optional<Group> group = this.findById(groupId);
        if(group.isEmpty()) return false;
        this.groupRepository.delete(group.get());
        return true;
    }

    @Override
    public List<Group> getGroupsWhoParents() {
        return this.groupRepository.getGroupsWhoParents();
    }
}
