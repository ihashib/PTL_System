package com.example.PTL_System.MasterNode;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MasterNodeRepo extends MongoRepository<MasterNode, String> {
}
