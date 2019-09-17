package com.tekchand.testapp.ui.main;

public class User {
    private String username;
    private String phoneNo;
    private String emailId;

    public User(String username, String phoneNo, String emailId) {
        this.username = username;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
