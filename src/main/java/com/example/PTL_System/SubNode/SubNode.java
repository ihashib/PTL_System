package com.example.PTL_System.SubNode;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "SubNode")
public class SubNode {
    @Id
    private String id;
    private String content;
    private String apWifiSsid;
    private String apWifiPW;      //encrypt later
    private String meshPrefix;
    private String meshPW;
    private String meshPort;
    private String masterIP;
    @Indexed(unique = true)
    private String ownIP;
    private int lightMode;
    private int alarmVol;
    private int lightBlinkMode;
    private int boxState;
    private LocalDateTime created;

    public SubNode(String content, String apWifiSsid, String apWifiPW, String meshPrefix, String meshPW, String meshPort, String masterIP, String ownIP, int lightMode, int alarmVol, int lightBlinkMode, int boxState) {
        this.content = content;
        this.apWifiSsid = apWifiSsid;
        this.apWifiPW = apWifiPW;
        this.meshPrefix = meshPrefix;
        this.meshPW = meshPW;
        this.meshPort = meshPort;
        this.masterIP = masterIP;
        this.ownIP = ownIP;
        this.lightMode = lightMode;
        this.alarmVol = alarmVol;
        this.lightBlinkMode = lightBlinkMode;
        this.boxState = boxState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getMeshPrefix() {
        return meshPrefix;
    }

    public void setMeshPrefix(String meshPrefix) {
        this.meshPrefix = meshPrefix;
    }

    public String getMeshPW() {
        return meshPW;
    }

    public void setMeshPW(String meshPW) {
        this.meshPW = meshPW;
    }

    public String getMeshPort() {
        return meshPort;
    }

    public void setMeshPort(String meshPort) {
        this.meshPort = meshPort;
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

    public int getLightMode() {
        return lightMode;
    }

    public void setLightMode(int lightMode) {
        this.lightMode = lightMode;
    }

    public int getAlarmVol() {
        return alarmVol;
    }

    public void setAlarmVol(int alarmVol) {
        this.alarmVol = alarmVol;
    }

    public int getLightBlinkMode() {
        return lightBlinkMode;
    }

    public void setLightBlinkMode(int lightBlinkMode) {
        this.lightBlinkMode = lightBlinkMode;
    }

    public int getBoxState() {
        return boxState;
    }

    public void setBoxState(int boxState) {
        this.boxState = boxState;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
