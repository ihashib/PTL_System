package com.example.PTL_System.ScanData;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@AllArgsConstructor
public class ScanDataController {
    private final ScanDataService scanDataService;

    private WebClient.Builder webClientBuilder;

    @PostMapping(value="api/ptl/ScanData/scan")
    public ScanData createScannData(@RequestBody ScanData scanData)
    {
        return scanDataService.createScanData(scanData, webClientBuilder);
    }

    @GetMapping(value="api/ptl/ScanData/all")
    public List<ScanData> getAllScanData()
    {
        return scanDataService.getAllScanData();
    }

    @PostMapping(value="api/ptl/SubNode/ScanData/{id}")
    public ScanData getScanDataByID(@PathVariable("id")String id) {
        return scanDataService.getScanDataById(id);
    }

    @PutMapping(value = "api/ptl/ScanData/update/{Id}")
    public ScanData updateScanDataById(@PathVariable("Id") String id, @RequestBody ScanData scanData){
        return scanDataService.updateScanDataById(id, scanData, webClientBuilder);
    }
}
