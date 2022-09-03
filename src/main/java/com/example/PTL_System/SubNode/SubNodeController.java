package com.example.PTL_System.SubNode;

import com.example.PTL_System.MasterNode.MasterNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class SubNodeController {
    private final SubNodeService subNodeService;

    @GetMapping(value="api/ptl/allSubNode")
    public List<SubNode>  getAllSubNodes()
    {
        return subNodeService.getAllSubNodes();
    }

    @PostMapping(value="api/ptl/createSubNode")
    public String createSubNode(@RequestBody SubNode subNode)
    {
        return subNodeService.createSubNode(subNode);
    }

    @PutMapping(value = "api/ptl/updateSubNodeById/{id}")
    public String updateSubNodeById(@PathVariable("id") String id, @RequestBody SubNode subNode)
    {
        return subNodeService.updateSubNodeById(id, subNode);
    }

    @DeleteMapping(value="api/ptl/deleteSubNodeById/{id}")
    public String deleteSubNodeById(@PathVariable String id)
    {
        return subNodeService.deleteSubNodeById(id);
    }

    @PostMapping(value = "api/ptl/SubNode/{id}/ack")
    public String getSubNodeACK(@PathVariable("id") String id, @RequestBody SubNode subNode) throws JsonProcessingException {
        return subNodeService.subNodeACK(id, subNode);
    }

    @GetMapping(value="api/ptl/SubNode/dummyResponse")
    public List<SubNode> getDummyResponse()
    {
        return subNodeService.getAllSubNodes();
    }
}
