package com.example.PTL_System.ScannerNode;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ScannerNodeService {
    private final ScannerNodeRepo scannerNodeRepo;

    public List<ScannerNode> getAllScannerNodes()
    {
        return scannerNodeRepo.findAll();
    }
}
