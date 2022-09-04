package com.example.PTL_System.ScannerNode;

import com.example.PTL_System.SubNode.SubNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ScannerNodeService {
    private final ScannerNodeRepo scannerNodeRepo;
    private final ScanDataRepo scanDataRepo;

    public List<ScannerNode> getAllScannerNodes()
    {
        return scannerNodeRepo.findAll();
    }

    public String createScannerNode(ScannerNode scannerNode)
    {
        ScannerNode insertedScannerNode = scannerNodeRepo.insert(scannerNode);

        return "Scanner Node created, id: "+insertedScannerNode.getId();
    }

    public String updateScannerNodeById(String id, ScannerNode scannerNode)
    {
        Optional<ScannerNode> scannerNodeOptional= scannerNodeRepo.findById(id);
        if(scannerNodeOptional.isPresent())
        {
            ScannerNode updateScannerNode = scannerNodeOptional.get();

            updateScannerNode.setScanContent(scannerNode.getScanContent() != null ? scannerNode.getScanContent():updateScannerNode.getScanContent());
            updateScannerNode.setApWifiSsid(scannerNode.getApWifiSsid() != null ? scannerNode.getApWifiSsid():updateScannerNode.getApWifiSsid());
            updateScannerNode.setApWifiPW(scannerNode.getApWifiPW() != null ? scannerNode.getApWifiPW():updateScannerNode.getApWifiPW());
            updateScannerNode.setConfimationFlag(scannerNode.getConfimationFlag() != null ? scannerNode.getConfimationFlag():updateScannerNode.getConfimationFlag());
            updateScannerNode.setCodeModeSelect(scannerNode.getCodeModeSelect() != null ? scannerNode.getCodeModeSelect():updateScannerNode.getCodeModeSelect());
            updateScannerNode.setMasterIP(scannerNode.getMasterIP() != null ? scannerNode.getMasterIP():updateScannerNode.getMasterIP());
            updateScannerNode.setOwnIP(scannerNode.getOwnIP() != null ? scannerNode.getOwnIP():updateScannerNode.getOwnIP());
            updateScannerNode.setScannerState(scannerNode.getScannerState() != null ? scannerNode.getScannerState():updateScannerNode.getScannerState());
            updateScannerNode.setAssignedParam(scannerNode.getAssignedParam() != null ? scannerNode.getAssignedParam():updateScannerNode.getAssignedParam());

            scannerNodeRepo.save(updateScannerNode);

            return "Scanner Node updated, id: "+updateScannerNode.getId();
        }
        else
        {
            return "No matching scanner Node id found";
        }
    }

    public String deleteScannerNodeById(String id)
    {
        try
        {
            scannerNodeRepo.deleteById(id);
            return "Deleted Scanner node, id: "+id;
        }
        catch (Exception e)
        {
            return "Delete fail, "+e;
        }
    }

    public String scannerNodeACK(String id, ScannerNode scannerNode) throws JsonProcessingException {
        Optional<ScannerNode> scannerNodeACKOptional = scannerNodeRepo.findById(id);

        if(scannerNodeACKOptional.isPresent())
        {
            ScannerNode scannerNodeACK = scannerNodeACKOptional.get();

            String uri = "http://localhost:8080/api/ptl/ScannerNode/dummyResponse";

            boolean flag = scannerNode.equals(getOtherAPI(uri, scannerNode));

            if (scannerNodeACK.equals(scannerNode) && flag) {
                scannerNodeACK.setScannerNodeAndServerAck(scannerNodeACK.ScannerNodeACKOK(true));

                scannerNodeRepo.save(scannerNodeACK);

                return "ScannerNode -> Master -> Server ACK status: " + scannerNodeACK.getScannerNodeAndServerAck();
            }
        }

        return "call post of scannernode from here and show on font end";
    }

    public ScanData createScanData(ScanData scanData, WebClient.Builder webClientBuilder)
    {
        System.out.println("Start");
        scanDataRepo.insert(scanData);

        String uri = "http://localhost:8080/api/ptl/SubNode/"+scanData.getSubNodeId();

        ScanData scanDatafromSubNode = setSubNode(uri, webClientBuilder, scanData);

        if(scanDatafromSubNode == null) return null;

        boolean subNodeIdFlag = scanData.getSubNodeId().equals(scanDatafromSubNode.getSubNodeId());
        boolean subNodeACKFlag = "ACKOK".equals(scanDatafromSubNode.getScanDataACK());

        if(subNodeIdFlag && subNodeACKFlag) {
            scanData.setScanDataACK(scanData.ScanDataACKOK(true));
            return scanData;
        }
        else {
            scanDataRepo.deleteById(scanData.getId());
        }
        return null;
    }

    public List<ScanData> getAllScanData()
    {
        return scanDataRepo.findAll();
    }

    public ScanData getScanDataById(String id)
    {
        return scanDataRepo.findById(id).get();
    }

    private ScannerNode getOtherAPI(String uri, ScannerNode scannerNode)
    {
        try{
            RestTemplate restTemplate = new RestTemplate();
            @SuppressWarnings("unchecked")
            List<ScannerNode> result = restTemplate.getForObject(uri, List.class);
            ObjectMapper mapper = new ObjectMapper();

            return mapper.convertValue(result.get(0), new TypeReference<ScannerNode>() {});
        }
        catch (Exception e)
        {
            System.out.println("ScannerNode -> Master -> Server ACK FAIL, Exception: "+e);
        }
        return null;
    }
    private ScanData setSubNode(String uri, WebClient.Builder webClientBuilder, ScanData scanData)
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


}
