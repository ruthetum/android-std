package com.example.usermanagement;

public class User {
    String userID;
    String userPW;
    String userName;
    String userAge;

    public User(String userID, String userPW, String userName, String userAge) {
        this.userID = userID;
        this.userPW = userPW;
        this.userName = userName;
        this.userAge = userAge;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPW() {
        return userPW;
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}
