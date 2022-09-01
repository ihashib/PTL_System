package com.example.PTL_System.ScannerNode;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class ScannerNode {
    @Id
    private String id;
    private String scanContent;
    private boolean confimation_flag;
    private int code_mode_select;
    private int scannerState;
    private String apWifiSsid;
    private String apWifiPW;        //encrypt later
    private String assignedParam;
    private String masterIP;
    private String ownIP;

    private LocalDateTime created;


    public ScannerNode(String scanContent, boolean confimation_flag, int code_mode_select, int scannerState, String apWifiSsid, String apWifiPW, String assignedParam, String masterIP, String ownIP, LocalDateTime created) {
        this.scanContent = scanContent;
        this.confimation_flag = confimation_flag;
        this.code_mode_select = code_mode_select;
        this.scannerState = scannerState;
        this.apWifiSsid = apWifiSsid;
        this.apWifiPW = apWifiPW;
        this.assignedParam = assignedParam;
        this.masterIP = masterIP;
        this.ownIP = ownIP;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScanContent() {
        return scanContent;
    }

    public void setScanContent(String scanContent) {
        this.scanContent = scanContent;
    }

    public boolean isConfimation_flag() {
        return confimation_flag;
    }

    public void setConfimation_flag(boolean confimation_flag) {
        this.confimation_flag = confimation_flag;
    }

    public int getCode_mode_select() {
        return code_mode_select;
    }

    public void setCode_mode_select(int code_mode_select) {
        this.code_mode_select = code_mode_select;
    }

    public int getScannerState() {
        return scannerState;
    }

    public void setScannerState(int scannerState) {
        this.scannerState = scannerState;
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

    public String getMasterIP() {
        return masterIP;
    }

    public void setMasterIP(String masterIP) {
        this.masterIP = masterIP;
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
