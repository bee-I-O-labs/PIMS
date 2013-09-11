package com.change.pims;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Creating the login activity.
 */
public class LoginActivity extends Activity {

    private String username;
    private String password;

    private EditText uName;
    private EditText pWord;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getLoginAuthentication();

        uName = (EditText)findViewById(R.id.txtUserName);
        pWord = (EditText)findViewById(R.id.txtPassword);

        final Button btnSignIn = (Button)findViewById(R.id.btnSignIn);
        final Button btnReg = (Button)findViewById(R.id.btnReg);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curUName = uName.getText().toString();
                String curPWord = pWord.getText().toString();

                if((curUName.equalsIgnoreCase(username) && curPWord.equalsIgnoreCase(password))){
                    Intent intent = new Intent(view.getContext(), SecurityActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * verifies the current user's authentication
     */
    public void getLoginAuthentication(){

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);;

        //commented out for testing... the values are already stored in the application
        /*SharedPreferences.Editor editor = preferences.edit();

        editor.putString("@string/uName", "pims");

        editor.putString("@string/pWord", "00000");

        editor.commit();*/

        /*
         * in a case where many users exist an array may be used
         * get all the available values
         */
        username = preferences.getString("@string/uName", "null");
        password = preferences.getString("@string/pWord", "null");

    }

}