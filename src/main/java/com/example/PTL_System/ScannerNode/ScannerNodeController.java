package com.example.PTL_System.ScannerNode;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ScannerNodeController {
    private final ScannerNodeService scannerNodeService;

    @GetMapping(value="api/ptl/allScannerNode")
    public List<ScannerNode> getAllScannerNodes()
    {
        return scannerNodeService.getAllScannerNodes();
    }

    @PostMapping(value="api/ptl/createScannerNode")
    public String createScannerNode(@RequestBody ScannerNode scannerNode)
    {
        return scannerNodeService.createScannerNode(scannerNode);
    }

    @PutMapping(value = "api/ptl/updateScannerNodeById/{id}")
    public String updateScannerNode(@PathVariable("id") String id, @RequestBody ScannerNode scannerNode)
    {
        return scannerNodeService.updateScannerNodeById(id, scannerNode);
    }

    @DeleteMapping(value="api/ptl/deleteScannerNodeById/{id}")
    public String deleteScannerNodeById(@PathVariable String id)
    {
        return scannerNodeService.deleteScannerNodeById(id);
    }
}
