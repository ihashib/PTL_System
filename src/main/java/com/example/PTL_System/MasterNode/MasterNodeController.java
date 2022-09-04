package com.example.PTL_System.MasterNode;

import com.example.PTL_System.ScannerNode.ScannerNodeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@AllArgsConstructor
public class MasterNodeController {
    private final MasterNodeService masterNodeService;

    @GetMapping(value="api/ptl/allMasterNode")
    public List<MasterNode> getAllMasterNodes()
    {
        return masterNodeService.getAllMasterNode();
    }

    @GetMapping(value="api/ptl/MasterNode/AckResp")
    public List<MasterNode> getDummyResponse()
    {
        return masterNodeService.getAllMasterNode();
    }

    @PostMapping(value = "api/ptl/createMasterNode")
    public String createMasterNode(@RequestBody MasterNode masterNode)
    {
        return masterNodeService.createMasterNode(masterNode);
    }

    @PutMapping(value="api/ptl/updateMasterNodeById/{id}")
    public String updateMasterNodeById(@PathVariable("id") String id, @RequestBody MasterNode masterNode)
    {
        return masterNodeService.updateMasterNodeById(id, masterNode);
    }

    @DeleteMapping(value="api/ptl/deleteMasterNodeById/{id}")
    public String deleteMasterNodeById(@PathVariable String id)
    {
        return masterNodeService.deleteMasterNodeById(id);
    }

    @PostMapping(value = "api/ptl/MasterNode/{id}/ack")
    public String getMasterACK(@PathVariable("id") String id, @RequestBody MasterNode masterNode) throws JsonProcessingException {
        return masterNodeService.masterNodeACK(id, masterNode);
    }
}
