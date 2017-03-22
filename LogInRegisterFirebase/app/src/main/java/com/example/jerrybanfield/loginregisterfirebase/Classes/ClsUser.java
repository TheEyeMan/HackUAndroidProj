package com.example.jerrybanfield.loginregisterfirebase.Classes;

import java.util.UUID;

/**
 * Created by User on 28/02/2017.
 */

public class ClsUser {
    public String userEmail;
    public String userPass;
    public String userName;
    public String userUuid;

    public ClsUser(){}

    public ClsUser(String userEmail,String userPass){
        this.userEmail = userEmail;
        this.userPass = userPass;
    }

    public ClsUser(String userEmail, String userPass, String userName) {
        this.userEmail = userEmail;
        this.userPass = userPass;
        this.userName = userName;
        this.userUuid = UUID.randomUUID().toString();
    }

    public ClsUser(String userEmail, String userPass, String userName, String userUuid) {
        this.userEmail = userEmail;
        this.userPass = userPass;
        this.userName = userName;
        this.userUuid = userUuid;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPass() {
        return userPass;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserUuid() {
        return userUuid;
    }

}
