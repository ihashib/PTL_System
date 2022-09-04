package com.example.PTL_System.SubNode;

import com.example.PTL_System.ScanData.ScanData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SubNodeService {
    private final SubNodeRepo subNodeRepo;

    public List<SubNode> getAllSubNodes()
    {
        return subNodeRepo.findAll();
    }

    public String createSubNode(SubNode subNode)
    {
        SubNode intertedSubNode = subNodeRepo.insert(subNode);
        return "Sub Node created, id: "+ intertedSubNode.getId();
    }

    public String updateSubNodeById(String id, SubNode subNode)
    {
        Optional<SubNode> subNodeOptional= subNodeRepo.findById(id);
        if(subNodeOptional.isPresent())
        {
            SubNode updateSubNode = subNodeOptional.get();

            updateSubNode.setContent(subNode.getContent() != null ? subNode.getContent():updateSubNode.getContent());
            updateSubNode.setApWifiSsid(subNode.getApWifiSsid() != null ? subNode.getApWifiSsid():updateSubNode.getApWifiSsid());
            updateSubNode.setApWifiPW(subNode.getApWifiPW() != null ? subNode.getApWifiPW():updateSubNode.getApWifiPW());
            updateSubNode.setMeshPrefix(subNode.getMeshPrefix() != null ? subNode.getMeshPrefix():updateSubNode.getMeshPrefix());
            updateSubNode.setMeshPW(subNode.getMeshPW() != null ? subNode.getMeshPW():updateSubNode.getMeshPW());
            updateSubNode.setMeshPort(subNode.getMeshPort() != null ? subNode.getMeshPort():updateSubNode.getMeshPort());
            updateSubNode.setMasterIP(subNode.getMasterIP() != null ? subNode.getMasterIP():updateSubNode.getMasterIP());
            updateSubNode.setOwnIP(subNode.getOwnIP() != null ? subNode.getOwnIP():updateSubNode.getOwnIP());
            updateSubNode.setLightMode(subNode.getLightMode() != 0 ? subNode.getLightMode():updateSubNode.getLightMode());
            updateSubNode.setAlarmVol(subNode.getAlarmVol() != 0 ? subNode.getAlarmVol():updateSubNode.getAlarmVol());
            updateSubNode.setLightBlinkMode(subNode.getLightBlinkMode() != 0 ? subNode.getLightBlinkMode():updateSubNode.getLightBlinkMode());
            updateSubNode.setBoxState(subNode.getBoxState() != 0 ? subNode.getBoxState():updateSubNode.getBoxState());

            subNodeRepo.save(updateSubNode);

            return "Sub Node updated, id: "+updateSubNode.getId();
        }
        else
        {
            return "No matching Sub Node id found";
        }
    }

    public String deleteSubNodeById(String id)
    {
        try
        {
            subNodeRepo.deleteById(id);
            return "Deleted Sub node, id: "+id;
        }
        catch (Exception e)
        {
            return "Delete fail, "+e;
        }
    }

    public String subNodeACK(String id, SubNode subNode) throws JsonProcessingException {
        Optional<SubNode> subNodeACKOptional = subNodeRepo.findById(id);

        if(subNodeACKOptional.isPresent())
        {
            SubNode subNodeACK = subNodeACKOptional.get();

            String uri = "http://localhost:8080/api/ptl/SubNode/AckResp";

            RestTemplate restTemplate = new RestTemplate();

            try {
                @SuppressWarnings("unchecked")
                List<SubNode> result = restTemplate.getForObject(uri, List.class);
                ObjectMapper mapper = new ObjectMapper();

                boolean flag = subNode.equals(mapper.convertValue(result.get(0), new TypeReference<SubNode>() {
                }));

                if (subNodeACK.equals(subNode) && flag) {
                    subNodeACK.setSubNodeAndServerAck(subNodeACK.SubNodeACKOK(true));

                    subNodeRepo.save(subNodeACK);

                    return "SubNode -> Master -> Server ACK status: " + subNodeACK.getSubNodeAndServerAck();
                }
            }
            catch (Exception e)
            {
                System.out.println("SubNode -> Master -> Server ACK FAIL, Exception: "+e);
            }
        }

        return "call post of subnode from here and show on font end";
    }

    public ScanData getSubNodeById(String id, ScanData scanData)
    {
        SubNode subNode = subNodeRepo.findById(id).get();

        if(subNode.getSubNodeAndServerAck().equals(subNode.SubNodeACKOK(true))
                && subNode.getId().equals(scanData.getSubNodeId())) {
            scanData.setScanDataACK(scanData.ScanDataACKOK(true));
        }

        return scanData;
    }
}
