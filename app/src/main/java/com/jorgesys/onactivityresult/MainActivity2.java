package com.jorgesys.onactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.google.android.material.switchmaterial.SwitchMaterial;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private boolean SwitchCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        SwitchMaterial mySwitch = findViewById(R.id.mySwitch);
        mySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.i(TAG, "onCheckedChanged() isChecked : " + isChecked);
            SwitchCheck = isChecked;
        });


        if(getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String name = bundle.getString("name") != null?bundle.getString("name") :"";
            boolean update = bundle.getBoolean("update");
            Log.i(TAG, "onActivityResult() name: " + name + " update: " + update);
        }

        Button btnQuit = findViewById(R.id.button_close);
        btnQuit.setOnClickListener(view -> saveIntent());


    }

    private void saveIntent() {
        Intent intent = new Intent();
        intent.putExtra("name", "Jorgesys");
        intent.putExtra("update", SwitchCheck);
        Log.d(TAG, "SaveIntent");
        setResult(Activity.RESULT_OK, intent);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("name", "Jorgesys");
        intent.putExtra("update", SwitchCheck);
        Log.d(TAG, "SaveIntent");
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}