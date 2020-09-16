package com.squad.group.model;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
@Data
public class Group {

    private String groupId , creatorId , name , isAnonymous , isPublic;

    @Relationship(type = "PC" , direction = Relationship.UNDIRECTED)
    private List<Role> subGroups = new ArrayList<>();

    @Relationship(type = "PC" , direction = Relationship.UNDIRECTED)
    private List<Role> superGroups = new ArrayList<>();

    @Relationship(type = "Squid" , direction = Relationship.INCOMING)
    private List<UserRole> users = new ArrayList<>();

}
