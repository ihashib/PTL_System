package com.example.PTL_System.SubNode;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubNodeRepo extends MongoRepository<SubNode, String> {
}
