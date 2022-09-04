package com.example.PTL_System.MasterNode;

import com.example.PTL_System.ScannerNode.ScannerNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Serial;
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

    public String masterNodeACK(String id, MasterNode masterNode) throws JsonProcessingException {
        Optional<MasterNode> masterNodeACKOptional = masterNodeRepo.findById(id);

        if(masterNodeACKOptional.isPresent())
        {
            MasterNode masterNodeACK = masterNodeACKOptional.get();

            String uri = "http://localhost:8080/api/ptl/MasterNode/AckResp";

            RestTemplate restTemplate = new RestTemplate();

            try {
                @SuppressWarnings("unchecked")
                List<MasterNode> result = restTemplate.getForObject(uri, List.class);
                ObjectMapper mapper = new ObjectMapper();

                boolean flag = masterNode.equals(mapper.convertValue(result.get(0), new TypeReference<MasterNode>() {
                }));

                if (masterNodeACK.equals(masterNode) && flag) {
                    masterNodeACK.setMasterAndServerAck(masterNodeACK.MasterACKOK(true));

                    masterNodeRepo.save(masterNodeACK);

                    return "Master -> Server ACK status: " + masterNodeACK.getMasterAndServerAck();
                }
            }
            catch (Exception e)
            {
                System.out.println("Master -> Server ACK FAIL, Exception: "+e);
            }
        }

        return "call post of masternode from here and show on font end";
    }

}
