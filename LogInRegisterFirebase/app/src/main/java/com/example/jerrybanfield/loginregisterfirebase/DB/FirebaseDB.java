package com.example.jerrybanfield.loginregisterfirebase.DB;

import android.content.Context;
import android.widget.Toast;

import com.example.jerrybanfield.loginregisterfirebase.Classes.ClsUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by User on 06/03/2017.
 */

public class FirebaseDB {
    Context context;
    public final String DB_TABLE_NAME_PARAM = "username";
    public final String DB_TABLE_PASS_PARAM = "password";
    public final String DB_TABLE_NICK_PARAM = "nickname";
    public final String DB_TABLE_UUID_PARAM = "UUID";
    public final String DB_TABLE_USERS = "Users";
    private DatabaseReference usersRoot;

    public FirebaseDB(Context context,String tableName){
        this.context = context;
        usersRoot = FirebaseDatabase.getInstance().getReference(DB_TABLE_USERS);
    }

    public void addNewUser(ClsUser tempStud){
        usersRoot.child(tempStud.getUserUuid()).setValue(new ClsUser(
                tempStud.getUserEmail(),
                tempStud.getUserPass(),
                tempStud.getUserName(),
                tempStud.getUserUuid()
        ));
    }

    public void logIn(ClsUser tempUser){

        DatabaseReference usersTableRoot = FirebaseDatabase.getInstance().getReference("Users");

        usersTableRoot.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

        Map<String,String> usersMap = new HashMap<String,String>();
        Iterator i = dataSnapshot.getChildren().iterator();
        int j = 0;
        while(i.hasNext()){
        usersMap.put(""+j,((DataSnapshot)i.next()).toString());
        j+=1;
        }
        String myText = "";
        //String myValue = dataSnapshot.getValue().toString();
        Map<String,Object> usersMap2 = new HashMap<String,Object>();
        for (Map.Entry<String, String> entry : usersMap.entrySet())
        {
        //usersMap2.put(entry.getValue().)
        System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        //loginEtEmail.setText(usersMap.toString());
        Toast.makeText(context, usersMap.toString(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
        Toast.makeText(context, "Error in DB connection", Toast.LENGTH_SHORT).show();
        }
        });
    }
}
