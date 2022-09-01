package com.example.PTL_System.SubNode;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SubNodeService {
    private final SubNodeRepo subNodeRepo;

    public List<SubNode> getAllSubNodes()
    {
        return subNodeRepo.findAll();
    }
}
