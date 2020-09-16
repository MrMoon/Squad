package com.squad.group.repository;

import com.squad.group.model.Group;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface GroupRepository extends Neo4jRepository<Group , Long> {
    Group getGroupByGroupId(String groupId);

    @Query("MATCH (am:Group)<-[pc:PC]-(u:User)-[s:Squid]->(dm:Group) return u , collect(am), collect(pc), collect(s), collect(dm)")
    List<Group> getGroupsWhoParents();
}
