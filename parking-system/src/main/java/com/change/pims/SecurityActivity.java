package com.change.pims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.change.pims.LoginActivity;
import com.change.pims.R;

/**
 * implementing the view for the security
 */
public class SecurityActivity extends Activity {

    private final static int capacity = 50;
    private int available = 50;
    private int is_open = 0;
    private int reserved = 0;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_view);

        final Button btnIncrease = (Button)findViewById(R.id.btnIncrease);
        final Button btnDecrease = (Button)findViewById(R.id.btnDecrease);
        final Button btnClose = (Button)findViewById(R.id.btnClose);

        final TextView txtCapacity = (TextView)findViewById(R.id.txtCapacity);
        final TextView txtAvailable = (TextView)findViewById(R.id.txtAvailable);
        final TextView txtIsOpen = (TextView)findViewById(R.id.txtIsOpen);
        final TextView txtReserved = (TextView)findViewById(R.id.txtReserved);

        txtCapacity.setText(String.valueOf(getCapacity()));
        txtAvailable.setText(String.valueOf(getAvailable()));
        txtIsOpen.setText(String.valueOf(getIs_open()));
        txtReserved.setText(String.valueOf(getReserved()));

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getAvailable() < 50){
                    setAvailable(getAvailable() + 1);
                    txtAvailable.setText(String.valueOf(getAvailable()));
                }
            }
        });

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getAvailable() > 0){
                    setAvailable(getAvailable() - 1);
                    txtAvailable.setText(String.valueOf(getAvailable()));
                }
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);

                //clears the back stack and sets the activity to its root state
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public void setIs_open(int is_open) {
        this.is_open = is_open;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailable() {
        return available;
    }

    public int getIs_open() {
        return is_open;
    }

    public int getReserved() {
        return reserved;
    }

}