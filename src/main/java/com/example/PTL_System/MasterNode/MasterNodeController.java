package com.example.PTL_System.MasterNode;

import com.example.PTL_System.ScannerNode.ScannerNodeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/PTL/MasterNode")
@AllArgsConstructor
public class MasterNodeController {
    private final MasterNodeService masterNodeService;

    public List<MasterNode> getAllMasterNodes()
    {
        return masterNodeService.getAllMasterNode();
    }
}
