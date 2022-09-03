package com.example.PTL_System.ScannerNode;

import com.example.PTL_System.SubNode.SubNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

            RestTemplate restTemplate = new RestTemplate();

            try {
                @SuppressWarnings("unchecked")
                List<ScannerNode> result = restTemplate.getForObject(uri, List.class);
                ObjectMapper mapper = new ObjectMapper();

                boolean flag = scannerNode.equals(mapper.convertValue(result.get(0), new TypeReference<ScannerNode>() {
                }));

                if (scannerNodeACK.equals(scannerNode) && flag) {
                    scannerNodeACK.setScannerNodeAndServerAck(scannerNodeACK.ScannerNodeACKOK(true));

                    scannerNodeRepo.save(scannerNodeACK);

                    return "ScannerNode -> Master -> Server ACK status: " + scannerNodeACK.getScannerNodeAndServerAck();
                }
            }
            catch (Exception e)
            {
                System.out.println("ScannerNode -> Master -> Server ACK FAIL, Exception: "+e);
            }
        }

        return "call post of scannernode from here and show on font end";
    }

    public String createScanData(ScanData scanData)
    {
       ScanData insertedScanData = scanDataRepo.save(scanData);

        return "Scanner Data created, id: "+insertedScanData.getId();
    }

    public List<ScanData> getAllScanData()
    {
        return scanDataRepo.findAll();
    }

}
