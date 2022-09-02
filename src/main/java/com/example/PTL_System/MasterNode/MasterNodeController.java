package com.example.PTL_System.MasterNode;

import com.example.PTL_System.ScannerNode.ScannerNodeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
