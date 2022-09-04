package com.example.PTL_System.ScanData;

import com.example.PTL_System.MasterNode.MasterNode;
import com.example.PTL_System.ScannerNode.ScannerNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ScanDataService {
    private final ScanDataRepo scanDataRepo;

    public ScanData createScanData(ScanData scanData, WebClient.Builder webClientBuilder)
    {
        System.out.println("Start");
        scanDataRepo.insert(scanData);

        String uri = "http://localhost:8080/api/ptl/SubNode/"+scanData.getSubNodeId();

        ScanData scanDatafromNode = postToNode(uri, webClientBuilder, scanData);

        if(checkScanData(scanData, scanDatafromNode)) {
            scanData.setScanDataACK(scanData.ScanDataACKOK(true));

            uri = "http://localhost:8080/api/ptl/ScannerNode/"+scanData.getScannerNodeId();

            scanDatafromNode = postToNode(uri, webClientBuilder, scanData);

            if(checkScanData(scanData, scanDatafromNode))
                return scanData;
        }
        else {
            scanDataRepo.deleteById(scanData.getId());
        }
        return null;
    }

    private boolean checkScanData(ScanData scanData, ScanData scanDatafromNode)
    {
        if(scanDatafromNode == null) return false;

        boolean nodeIdFlag = scanData.getSubNodeId().equals(scanDatafromNode.getSubNodeId());
        boolean nodeACKFlag = "ACKOK".equals(scanDatafromNode.getScanDataACK());

        if(nodeIdFlag && nodeACKFlag) {
            return true;
        }
        return false;
    }

    public List<ScanData> getAllScanData()
    {
        return scanDataRepo.findAll();
    }

    public ScanData getScanDataById(String id)
    {
        return scanDataRepo.findById(id).get();
    }
    private ScanData postToNode(String uri, WebClient.Builder webClientBuilder, ScanData scanData)
    {
        System.out.println(scanData.getId());
        try {
            ScanData response = webClientBuilder.build().post()
                    .uri(new URI(uri))
                    .body(BodyInserters.fromValue(scanData))
                    .retrieve()
                    .bodyToMono(ScanData.class)
                    .block();
            System.out.println(response);
            return response;
        }
        catch (Exception e)
        {
            System.out.println("Server -> SubNode GET FAIL, Exception: "+e);
        }
        return null;
    }

    public ScanData updateScanDataById(String id, ScanData scanData, WebClient.Builder webClientBuilder)
    {
        Optional<ScanData> scanDataOptional = scanDataRepo.findById(id);
        if(scanDataOptional.isPresent())
        {
            ScanData updateScanData = scanDataOptional.get();

            boolean updateFlag = updateScanData.getTaskStatus().equals("NOTDONE");
            boolean currentFlag = scanData.getTaskStatus().equals("DONE");

            System.out.println(updateFlag);
            System.out.println(currentFlag);

            if(updateFlag && currentFlag) {

                updateScanData.setTaskStatus(scanData.getTaskStatus() != null ? scanData.getTaskStatus() : updateScanData.getTaskStatus());

                scanDataRepo.save(updateScanData);

                String uri = "http://localhost:8080/api/ptl/ScannerNode/"+updateScanData.getScannerNodeId();

                ScanData scanDatafromNode = postToNode(uri, webClientBuilder, updateScanData);

                if(checkScanData(scanData, scanDatafromNode))
                    return updateScanData;
            }
        }

        return null;
    }
}
