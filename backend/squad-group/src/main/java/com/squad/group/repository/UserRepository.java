package com.squad.group.repository;

import com.squad.group.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User , Long> {

}
