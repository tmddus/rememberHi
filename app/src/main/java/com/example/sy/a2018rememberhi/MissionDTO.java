package com.example.sy.a2018rememberhi;

public class MissionDTO {
    private String missionAlarm;
    private int missionComple;
    private String StringTitle;

    public MissionDTO() {}

    public MissionDTO(String StringTitle, String missionAlarm, int missionComple) {
        super();
        this.StringTitle = StringTitle;
        this.missionAlarm = missionAlarm;
        this.missionComple = missionComple;
    }
    public String getStringTitle() {
        return StringTitle;
    }
    public void setStringTitle(String StringTitle) {
        this.StringTitle = StringTitle;
    }
    public String getMissionAlarm() {
        return missionAlarm;
    }
    public void setMissionAlarm(String missionAlarm) {
        this.missionAlarm = missionAlarm;
    }
    public int getMissionComple() {
        return missionComple;
    }
    public void setMissionComple(int missionComple) {
        this.missionComple = missionComple;
    }


}
