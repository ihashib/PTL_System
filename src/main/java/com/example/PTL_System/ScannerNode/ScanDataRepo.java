package com.example.PTL_System.ScannerNode;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScanDataRepo extends MongoRepository<ScanData, String> {
}
