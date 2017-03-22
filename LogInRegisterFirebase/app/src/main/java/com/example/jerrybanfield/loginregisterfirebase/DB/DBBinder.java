package com.example.jerrybanfield.loginregisterfirebase.DB;

import android.content.Context;

import com.example.jerrybanfield.loginregisterfirebase.Classes.ClsUser;

/**
 * Created by User on 06/03/2017.
 */

public class DBBinder {

    BackendlessDB backendlessDB;
    public DBBinder (Context context){
        backendlessDB = new BackendlessDB(context);
    }

    public void addNewUser(ClsUser tempUser){
        //FIrebaseDB.addStudent(tempStud);
        backendlessDB.addNewUser(tempUser);
    }

    public boolean logIn(ClsUser tempUser){
        return backendlessDB.logIn(tempUser);

    }
    /**public static void addGrade (Grades grade){
        FIrebaseDB.addGrade(grade);
    }*/
}
