package com.example.PTL_System.ScannerNode;

import com.example.PTL_System.SubNode.SubNode;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScannerNodeRepo extends MongoRepository<ScannerNode, String> {
}
