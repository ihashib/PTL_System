package com.example.PTL_System.ScanData;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScanDataRepo extends MongoRepository<ScanData, String> {
}
