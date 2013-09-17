package com.change.pims;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Creating the login activity.
 */
public class LoginActivity extends Activity {

    private String username[];
    private String password[];

    private EditText uName;
    private EditText pWord;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uName = (EditText)findViewById(R.id.txtUserName);
        pWord = (EditText)findViewById(R.id.txtPassword);

        final Button btnSignIn = (Button)findViewById(R.id.btnSignIn);
        final Button btnReg = (Button)findViewById(R.id.btnReg);

        LoginManagerDatabase lm = new LoginManagerDatabase(getApplicationContext());

        //lm.registerUser("steel", "00000");
        //lm.registerUser("pims", "00000");

        loadData(lm);

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
                if(getLoginAuthentication()){
                    Intent intent = new Intent(view.getContext(), SecurityActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     *
     * @param manager the database manager
     */
    public void loadData(LoginManagerDatabase manager){

        username = new String[manager.getUsernames().length];
        username = manager.getUsernames();

        password = new String[manager.getPasswords().length];
        password = manager.getPasswords();

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();

        for(int i = 0;i < username.length;i++){

            editor.putString("@string/uName", username[i]);
            editor.putString("@string/pWord", password[i]);

        }
        editor.commit();

    }

    /**
     * verifies the current user's authentication
     */
    public Boolean getLoginAuthentication(){

        String curUName = uName.getText().toString();
        String curPWord = pWord.getText().toString();
        int c = 0;

        for(int i = 0;i < username.length;i++){
            if(curUName.equalsIgnoreCase(username[i])){
                c++;
                break;
            }
        }

        for(int i = 0;i < username.length;i++){
            if(curPWord.equalsIgnoreCase(password[i])){
                c++;
                break;
            }
        }

        if(c == 2){
            return true;
        }
        else{
            return false;
        }

    }

}