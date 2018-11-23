package com.example.sy.a2018rememberhi;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UserDTO {

    private String userName;
    private String userID;
    private String userPWD;
    private String userChildPhone;
    private String userPhone;
    private int userGender;
    private int userBirth;
    private int userCheckList;

    public UserDTO() {}
    public UserDTO(String userName, String userID ,String userPWD,String userChildPhone,String userPhone,int userGender, int userBirth, int userCheckList) {
        this.userName = userName;
        this.userID = userID;
        this.userPWD = userPWD;
        this.userChildPhone = userChildPhone;
        this.userPhone = userPhone;
        this.userGender = userGender;
        this.userBirth = userBirth;
        this.userCheckList = userCheckList;
    }
    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getUserPWD() {
        return userPWD;
    }
    public void setUserPWD(String userPWD) {
        this.userPWD = userPWD;
    }
    public String getUserChildPhone() {
        return userChildPhone;
    }
    public void setUserChildPhone(String userChildPhone) {
        this.userChildPhone = userChildPhone;
    }
    public String getUserPhone()
    {
        return userPhone;
    }
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public int getUserGender() {
        return userGender;
    }
    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }
    public int getUserBirth() {
        return userBirth;
    }
    public void setUserBirth(int userBirth) {
        this.userBirth = userBirth;
    }
    public int getUserCheckList() {
        return userCheckList;
    }
    public void setUserCheckList(int userCheckList) {
        this.userCheckList = userCheckList;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("userName", userName);
        result.put("userID", userID);
        result.put("userPWD", userPWD);
        result.put("userChildPhone", userChildPhone);
        result.put("userPhone", userPhone);
        result.put("userGender", userGender);
        result.put("userBirth", userBirth);
        result.put("userCheckList", userCheckList);
        return result;
    }


}
