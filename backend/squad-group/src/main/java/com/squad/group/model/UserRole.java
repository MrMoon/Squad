package com.squad.group.model;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Data
@RelationshipEntity(type = "Squid")
public class UserRole {

    List<String> roles = new ArrayList<>();
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private User user;

    @EndNode
    private Group squad;

}
