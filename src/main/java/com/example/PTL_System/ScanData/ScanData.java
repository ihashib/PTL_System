package com.example.PTL_System.ScanData;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "ScanData")
public class ScanData {
    @Id
    private String id;
    private String scannerId;
    private String scanContent;
    private String scannerMode;
    private String subNodeId;
    private String scannerNodeId;
    private String scanDataACK;
    private String taskStatus;
    private LocalDateTime created;

    public ScanData() {
    }

    public ScanData(String id, String scannerId, String scanContent, String scannerMode, String subNodeId,
                    String scannerNodeId, String scanDataACK, String taskStatus, LocalDateTime created) {
        this.id = id;
        this.scannerId = scannerId;
        this.scanContent = scanContent;
        this.scannerMode = scannerMode;
        this.subNodeId = subNodeId;
        this.scannerNodeId = scannerNodeId;
        this.scanDataACK = scanDataACK;
        this.taskStatus = taskStatus;
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScannerId() {
        return scannerId;
    }

    public void setScannerId(String scannerId) {
        this.scannerId = scannerId;
    }

    public String getScanContent() {
        return scanContent;
    }

    public void setScanContent(String scanContent) {
        scanContent = scanContent;
    }

    public String getScannerMode() {
        return scannerMode;
    }

    public void setScannerMode(String scannerMode) {
        this.scannerMode = scannerMode;
    }

    public String getSubNodeId() {
        return subNodeId;
    }

    public void setSubNodeId(String subNodeId) {
        this.subNodeId = subNodeId;
    }

    public String getScanDataACK() {
        return scanDataACK;
    }

    public void setScanDataACK(String scanDataACK) {
        this.scanDataACK = scanDataACK;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String ScanDataACKOK(boolean flag)
    {
        if(flag)
            return "ACKOK";
        else
            return "ACKFAIL";
    }

    public String getScannerNodeId() {
        return scannerNodeId;
    }

    public void setScannerNodeId(String scannerNodeId) {
        this.scannerNodeId = scannerNodeId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id + '\'' +
                ", scannerId=" + scannerId + "" +
                ", scanContent=" + scanContent + "" +
                ", scannerMode=" + scannerMode + "" +
                ", subNodeId=" + subNodeId + "" +
                ", scanDataACK=" + scanDataACK + "" +
                ", created=" + created +
                '}';
    }
}

