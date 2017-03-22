package com.example.jerrybanfield.loginregisterfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jerrybanfield.loginregisterfirebase.Classes.ClsUser;
import com.example.jerrybanfield.loginregisterfirebase.DB.DBBinder;

import java.util.UUID;

public class Register extends AppCompatActivity {

    Context context;
    DBBinder dbBinder;
    private EditText registerEtEmail, registerEtUserName, registerEtPassword, registerEtRepeatPassword;
    private Button registerBtnRegister, registerBtnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setPointers();
        setListeners();
    }

    private void setPointers(){
        this.context = this;
        registerEtEmail = (EditText) findViewById(R.id.registerEtEmail);
        registerEtUserName = (EditText) findViewById(R.id.registerEtUserName);
        registerEtPassword = (EditText) findViewById(R.id.registerEtPassword);
        registerEtRepeatPassword = (EditText) findViewById(R.id.registerEtRepeatPassword);
        registerBtnRegister = (Button) findViewById(R.id.registerBtnRegister);
        registerBtnCancel = (Button) findViewById(R.id.registerBtnCancel);
        dbBinder = new DBBinder(context);
    }
    private void setListeners(){
        registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        registerBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelRegistration();
            }
        });
    }

    private void registerUser(){
        //create UUID for new user
        String userUUID = UUID.randomUUID().toString();
        //saving the new users details under the UUID
        ClsUser tempUser = new ClsUser(
                registerEtEmail.getText().toString(),
                registerEtPassword.getText().toString(),
                registerEtUserName.getText().toString());
        dbBinder.addNewUser(tempUser);
    }

    private void cancelRegistration(){
        Intent intent = new Intent(context,Login.class);
        startActivity(intent);
        finish();
    }
}
