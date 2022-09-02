package com.example.PTL_System.ScannerNode;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "ScannerNode")
public class ScannerNode {
    @Id
    private String id;
    private String scanContent;
    private String confimationFlag;
    private String CodeModeSelect;
    private String scannerState;
    private String apWifiSsid;
    private String apWifiPW;        //encrypt later
    private String assignedParam;
    private String masterIP;
    @Indexed(unique = true)
    private String ownIP;

    private LocalDateTime created;


    public ScannerNode(String scanContent, String confimationFlag, String CodeModeSelect, String scannerState, String apWifiSsid, String apWifiPW, String assignedParam, String masterIP, String ownIP) {
        this.scanContent = scanContent;
        this.confimationFlag = confimationFlag;
        this.CodeModeSelect = CodeModeSelect;
        this.scannerState = scannerState;
        this.apWifiSsid = apWifiSsid;
        this.apWifiPW = apWifiPW;
        this.assignedParam = assignedParam;
        this.masterIP = masterIP;
        this.ownIP = ownIP;
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

    public String getConfimationFlag() {
        return confimationFlag;
    }

    public void setConfimationFlag(String confimation_flag) {
        this.confimationFlag = confimation_flag;
    }

    public String getCodeModeSelect() {
        return CodeModeSelect;
    }

    public void setCodeModeSelect(String code_mode_select) {
        this.CodeModeSelect = code_mode_select;
    }

    public String getScannerState() {
        return scannerState;
    }

    public void setScannerState(String scannerState) {
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
