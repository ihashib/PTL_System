package com.example.PTL_System.MasterNode;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class MasterNode {
    @Id
    private String id;
    private String scannerNodeCount;
    private String subNodeCount;
    private boolean meshNetworkState;
    private String apWifiSsid;
    private String apWifiPW;        //encrypt later
    private String assignedParam;
    @Indexed(unique = true)
    private String ownIP;

    private LocalDateTime created;

    public MasterNode(String scannerNodeCount, String subNodeCount, boolean meshNetworkState, String apWifiSsid, String apWifiPW, String assignedParam, String ownIP, LocalDateTime created) {
        this.scannerNodeCount = scannerNodeCount;
        this.subNodeCount = subNodeCount;
        this.meshNetworkState = meshNetworkState;
        this.apWifiSsid = apWifiSsid;
        this.apWifiPW = apWifiPW;
        this.assignedParam = assignedParam;
        this.ownIP = ownIP;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScannerNodeCount() {
        return scannerNodeCount;
    }

    public void setScannerNodeCount(String scannerNodeCount) {
        this.scannerNodeCount = scannerNodeCount;
    }

    public String getSubNodeCount() {
        return subNodeCount;
    }

    public void setSubNodeCount(String subNodeCount) {
        this.subNodeCount = subNodeCount;
    }

    public boolean isMeshNetworkState() {
        return meshNetworkState;
    }

    public void setMeshNetworkState(boolean meshNetworkState) {
        this.meshNetworkState = meshNetworkState;
    }

    public String getApWifiSsid() {
        return apWifiSsid;
    }

    public void setApWifiSsid(String apWifiSsid) {
        this.apWifiSsid = apWifiSsid;
    }

    public String getApWifiPW() {
        return apWifiPW;
    }

    public void setApWifiPW(String apWifiPW) {
        this.apWifiPW = apWifiPW;
    }

    public String getAssignedParam() {
        return assignedParam;
    }

    public void setAssignedParam(String assignedParam) {
        this.assignedParam = assignedParam;
    }

    public String getOwnIP() {
        return ownIP;
    }

    public void setOwnIP(String ownIP) {
        this.ownIP = ownIP;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
