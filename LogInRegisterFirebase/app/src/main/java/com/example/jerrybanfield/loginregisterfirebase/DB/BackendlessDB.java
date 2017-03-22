package com.example.jerrybanfield.loginregisterfirebase.DB;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.jerrybanfield.loginregisterfirebase.Classes.ClsUser;
import com.example.jerrybanfield.loginregisterfirebase.R;

/**
 * Created by User on 06/03/2017.
 */

public class BackendlessDB {
    Context context;
    private final String DB_TABLE_USERNAME_PARAM = "userName";
    private final String DB_TABLE_NAME_PARAM = "Users";
    private static final String DB_TABLE_UUID_PARAM = "UUID";
    private final String TAG = "BackendlessDB";

    public BackendlessDB (Context context){
        this.context = context;
        Backendless.initApp(context,
                context.getResources().getString(R.string.app_id),
                context.getResources().getString(R.string.app_andr_sec_key),
                context.getResources().getString(R.string.app_v)
        );
    }

    public void addNewUser(ClsUser tempStud){
        //make progress dialog
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setTitle("updating data");
        pd.setMessage("please wait, adding user....");
        pd.show();
        BackendlessUser user = new BackendlessUser();
        user.setEmail(tempStud.getUserEmail());
        user.setPassword(tempStud.getUserPass());
        user.setProperty(""+DB_TABLE_UUID_PARAM, tempStud.getUserUuid());
        user.setProperty(""+DB_TABLE_USERNAME_PARAM, tempStud.getUserName());
        Backendless.UserService.register(user, new BackendlessCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                //show the respone in log
                Log.e(TAG, "handleResponse: "+response.toString() );
                pd.dismiss();
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("Fault", "handleFault: "+fault.getCode());
                int myErrorCode = Integer.parseInt(fault.getCode());
                switch (myErrorCode)
                {
                    case 3033:
                        Log.e(TAG, "user exists!" );
                        break;
                    case 3040:
                        Log.e(TAG, "Email format error!" );
                        break;
                    default :
                        Log.e(TAG, "Default error number "+myErrorCode);
                        break;
                }
                pd.dismiss();
            }
        });
    }

    public boolean logIn(ClsUser tempUser){
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setTitle("User Login");
        pd.setMessage("Searching for user...");
        pd.show();

        Backendless.UserService.login(tempUser.getUserEmail(), tempUser.getUserPass(), new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                pd.dismiss();
                Toast.makeText(context, "User login", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "handleResponse: user login, you can run activity" );
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                pd.dismiss();
                Toast.makeText(context, "Error in user login", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "handleFault: error login"+fault.getCode());
            }
        });
        return Backendless.UserService.isValidLogin();
    }
}
