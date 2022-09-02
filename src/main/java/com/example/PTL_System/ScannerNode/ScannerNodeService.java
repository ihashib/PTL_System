package com.example.PTL_System.ScannerNode;

import com.example.PTL_System.SubNode.SubNode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ScannerNodeService {
    private final ScannerNodeRepo scannerNodeRepo;

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
}
