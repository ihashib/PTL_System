package com.example.PTL_System.ScannerNode;

import com.example.PTL_System.ScanData.ScanData;
import com.example.PTL_System.SubNode.SubNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

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

    @PostMapping(value = "api/ptl/ScannerNode/{id}/ack")
    public String getScannerNodeACK(@PathVariable("id") String id, @RequestBody ScannerNode scannerNode) throws JsonProcessingException {
        return scannerNodeService.scannerNodeACK(id, scannerNode);
    }

    @GetMapping(value="api/ptl/ScannerNode/AckResp")
    public List<ScannerNode> getDummyResponse()
    {
        return scannerNodeService.getAllScannerNodes();
    }

    @PostMapping(value="api/ptl/ScannerNode/{id}")
    public ScanData getSubNodeById(@PathVariable("id")String id, @RequestBody ScanData scanData)
    {
        return scannerNodeService.setScanDataToScannerNodeById(id, scanData);
    }

}
