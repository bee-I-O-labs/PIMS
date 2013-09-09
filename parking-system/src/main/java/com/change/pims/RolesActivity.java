package com.change.pims;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 *should contain the gridview that consist of both the security and the admin
 */
public class RolesActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_roles);
        GridView gridview = (GridView)findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int iconIndex,
                                    long id) {
            //Do something when an icon is clicked e.g
                if (iconIndex == 0){
                    Toast.makeText(RolesActivity.this, "Icon 0 clicked", Toast.LENGTH_SHORT).show();
                }
                else if (iconIndex == 1){
                    Toast.makeText(RolesActivity.this, "Icon 1 clicked", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}