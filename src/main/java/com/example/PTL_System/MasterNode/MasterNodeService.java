package com.example.PTL_System.MasterNode;

import com.example.PTL_System.ScannerNode.ScannerNode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MasterNodeService {
    private final MasterNodeRepo masterNodeRepo;

    public List<MasterNode> getAllMasterNode()
    {
        return masterNodeRepo.findAll();
    }

    public String createMasterNode(MasterNode masterNode)
    {
        MasterNode insertedMasterNode = masterNodeRepo.insert(masterNode);

        return "Master Node Created, id: "+insertedMasterNode.getId();
    }

    public String updateMasterNodeById(String id, MasterNode masterNode)
    {
        Optional<MasterNode> masterNodeOptional = masterNodeRepo.findById(id);
        if(masterNodeOptional.isPresent())
        {
            MasterNode updateMasterNode = masterNodeOptional.get();

            updateMasterNode.setScannerNodeCount(masterNode.getScannerNodeCount() != null ? masterNode.getScannerNodeCount():updateMasterNode.getScannerNodeCount());
            updateMasterNode.setApWifiSsid(masterNode.getApWifiSsid() != null ? masterNode.getApWifiSsid():updateMasterNode.getApWifiSsid());
            updateMasterNode.setApWifiPW(masterNode.getApWifiPW() != null ? masterNode.getApWifiPW():updateMasterNode.getApWifiPW());
            updateMasterNode.setSubNodeCount(masterNode.getSubNodeCount() != null ? masterNode.getSubNodeCount():updateMasterNode.getSubNodeCount());
            updateMasterNode.setMeshNetworkState(masterNode.getMeshNetworkState() != null ? masterNode.getMeshNetworkState():updateMasterNode.getMeshNetworkState());
            updateMasterNode.setOwnIP(masterNode.getOwnIP() != null ? masterNode.getOwnIP():updateMasterNode.getOwnIP());
            updateMasterNode.setAssignedParam(masterNode.getAssignedParam() != null ? masterNode.getAssignedParam():updateMasterNode.getAssignedParam());

            masterNodeRepo.save(updateMasterNode);

            return "Master Node updated, id: "+updateMasterNode.getId();
        }
        else
        {
            return "No matching scanner Node id found";
        }
    }

    public String deleteMasterNodeById(String id)
    {
        try
        {
            masterNodeRepo.deleteById(id);
            return "Deleted Master node, id: "+id;
        }
        catch (Exception e)
        {
            return "Delete fail, "+e;
        }
    }
}
