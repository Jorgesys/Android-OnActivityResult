package com.jorgesys.onactivityresult;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

/*AndroidX Activity Result APIs — The new way! */
public class MainActivity extends AppCompatActivity {

    private static  final String TAG = "MainActivity";

    // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
    // You need to create a launcher variable inside onAttach or onCreate or global, i.e, before the activity is displayed
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.i(TAG, "onActivityResult() " + result.getResultCode());
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Here, no request code
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        String name = bundle.getString("name");
                        boolean update = bundle.getBoolean("update");
                        // your operation....
                        Log.i(TAG, "onActivityResult() Activity.RESULT_OK");
                        Log.i(TAG, "onActivityResult() name: "+ name+ " Activity update? : "+update );
                    }else{
                        Log.i(TAG, "onActivityResult() ");
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOpenActivity = findViewById(R.id.mybutton);
        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openYourActivity();
            }
        });

        loadFragment(new ListadoFragment());

    }

    public void openYourActivity() {
        Log.i(TAG, "openYourActivity() ");
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("name", "Jorgesys from Activity!");
        intent.putExtra("update", false);
        mStartForResult.launch(intent);
    }

    private void loadFragment(Fragment fragment) {
        Log.i(TAG, "loadFragment() ");
        // create a FragmentManager
        FragmentManager fm = getSupportFragmentManager();
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}