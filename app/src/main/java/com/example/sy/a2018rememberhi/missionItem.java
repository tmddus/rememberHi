package com.example.sy.a2018rememberhi;

public class missionItem {

    int success;
    String missionText;

    public missionItem() { }

    public missionItem(int success, String missionText) {
        this.success = success;
        this.missionText = missionText;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMissionText() {
        return missionText;
    }

    public void setMissionText(String missionText) {
        this.missionText = missionText;
    }

}
