package com.example.PTL_System.ScannerNode;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/PTL/ScannerNode")
@AllArgsConstructor
public class ScannerNodeController {
    private final ScannerNodeService scannerNodeService;

    public List<ScannerNode> getAllScannerNodes()
    {
        return scannerNodeService.getAllScannerNodes();
    }
}
