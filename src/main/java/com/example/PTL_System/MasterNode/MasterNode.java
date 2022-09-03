package com.example.PTL_System.MasterNode;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "MasterNode")
public class MasterNode {
    @Id
    private String id;
    private String scannerNodeCount;
    private String subNodeCount;
    private String meshNetworkState;
    private String apWifiSsid;
    private String apWifiPW;        //encrypt later
    private String assignedParam;
    @Indexed(unique = true)
    private String ownIP;
    private String masterAndServerAck;

    private LocalDateTime created;

    public MasterNode() {
    }

    public MasterNode(String id, String scannerNodeCount, String subNodeCount, String meshNetworkState,
                      String apWifiSsid, String apWifiPW, String assignedParam, String ownIP, String masterAndServerAck, LocalDateTime created) {
        this.id = id;
        this.scannerNodeCount = scannerNodeCount;
        this.subNodeCount = subNodeCount;
        this.meshNetworkState = meshNetworkState;
        this.apWifiSsid = apWifiSsid;
        this.apWifiPW = apWifiPW;
        this.assignedParam = assignedParam;
        this.ownIP = ownIP;
        this.masterAndServerAck = masterAndServerAck;
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

    public String isMeshNetworkState() {
        return meshNetworkState;
    }

    public void setMeshNetworkState(String meshNetworkState) {
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

    public String getMasterAndServerAck() {
        return masterAndServerAck;
    }

    public String MasterACKOK(boolean flag)
    {
        if(flag)
            return "ACKOK";
        else
            return "ACKFAIL";
    }

    public void setMasterAndServerAck(String masterAndServerAck) {
        this.masterAndServerAck = masterAndServerAck;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id + "" +
                ", scannerNodeCount=" + scannerNodeCount + "" +
                ", subNodeCount=" + subNodeCount + "" +
                ", meshNetworkState=" + meshNetworkState + "" +
                ", apWifiSsid=" + apWifiSsid + "" +
                ", apWifiPW=" + apWifiPW + "" +
                ", assignedParam=" + assignedParam + "" +
                ", ownIP=" + ownIP + "" +
                ", masterAndServerAck=" + masterAndServerAck + "" +
                ", created=" + created +
                '}';
    }
}
