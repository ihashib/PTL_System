package com.example.PTL_System.SubNode;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/LTP/SubNode")
@AllArgsConstructor
public class SubNodeController {
    private final SubNodeService subNodeService;

    @GetMapping
    public List<SubNode>  getAllSubNodes()
    {
        return subNodeService.getAllSubNodes();
    }
}
