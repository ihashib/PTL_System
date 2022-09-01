package com.example.PTL_System.MasterNode;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MasterNodeService {
    private final MasterNodeRepo masterNodeRepo;

    public List<MasterNode> getAllMasterNode()
    {
        return masterNodeRepo.findAll();
    }
}
