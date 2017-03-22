package com.example.jerrybanfield.loginregisterfirebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jerrybanfield.loginregisterfirebase.Classes.ClsUser;
import com.example.jerrybanfield.loginregisterfirebase.DB.DBBinder;

public class Login extends AppCompatActivity {

    public final String DB_TABLE_NAME_PARAM = "username";
    public final String DB_TABLE_PASS_PARAM = "password";
    public final String DB_TABLE_NICK_PARAM = "nickname";
    public final String DB_TABLE_UUID_PARAM = "UUID";
    public final String DB_TABLE_USERS = "Users";

    public  Context context;
    private Button loginBtnLogin,loginBtnRegister;
    private EditText loginEtEmail,loginEtPassword;
    private CheckBox loginChbStayLogged;
    DBBinder dbBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setPointers();
        setListeners();
    }

    private void setPointers(){
        this.context = this;
        dbBinder = new DBBinder(context);
        loginBtnLogin = (Button)findViewById(R.id.loginBtnLogin);
        loginBtnRegister = (Button)findViewById(R.id.loginBtnRegister);
        loginEtEmail = (EditText)findViewById(R.id.loginEtEmail);
        loginEtPassword = (EditText)findViewById(R.id.loginEtPassword);
        loginChbStayLogged = (CheckBox)findViewById(R.id.loginChbStayLogged);
    }
    private void setListeners(){
        loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        loginBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register(){
        Intent intent = new Intent(context,Register.class);
        startActivity(intent);
    }

    private void login(){
        //dbBinder.logIn(new ClsUser(loginEtEmail.getText().toString(),loginEtPassword.getText().toString()));
        Toast.makeText(context, ""+dbBinder.logIn(new ClsUser(loginEtEmail.getText().toString(),loginEtPassword.getText().toString())), Toast.LENGTH_SHORT).show();
    }
    /**
    +972545228649
    +972048342227
    048342227
    0545228649
    18005551234
     +97254
     +97204
     04
     05
     1800
    "^( (+?\\d{3}) (//d{7}) )&"*/
}
