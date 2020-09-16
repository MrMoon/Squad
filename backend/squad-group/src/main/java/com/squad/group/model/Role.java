package com.squad.group.model;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Data
@RelationshipEntity(type = "PC")
public class Role {

    List<String> roles = new ArrayList<>();
    @Id
    @GeneratedValue
    private Long id;
    @StartNode
    private Group mainGroup;

    @EndNode
    private Group subGroup;
}
