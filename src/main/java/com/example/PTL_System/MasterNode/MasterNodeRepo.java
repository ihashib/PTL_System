package com.example.PTL_System.MasterNode;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterNodeRepo extends MongoRepository<MasterNode, String> {
}
