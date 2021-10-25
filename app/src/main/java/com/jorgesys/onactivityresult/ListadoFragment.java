package com.jorgesys.onactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

public class ListadoFragment extends Fragment {

    public static final String TAG = "ListadoFragment";

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.i(TAG, "onActivityResult() " + result.getResultCode());
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        String name = bundle.getString("name");
                        boolean update = bundle.getBoolean("update");
                        // your operation....
                        Log.i(TAG, "onActivityResult() Activity.RESULT_OK");
                        Log.i(TAG, "onActivityResult() name: "+ name+ " Fragment update? : "+update );
                    }
                }
            });

    public static ListadoFragment newInstance(Bundle arguments){
        ListadoFragment f = new ListadoFragment();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Button btnStartActivity = view.findViewById(R.id.mybuttonFragment);
        btnStartActivity.setOnClickListener(view1 -> openYourActivity());
        return view;
    }

    public void openYourActivity() {
        Log.i(TAG, "openYourActivity() ");
        Intent intent = new Intent(getActivity(), MainActivity2.class);
        //intent.putExtra("name", "Jorgesys from Fragment!");
        //intent.putExtra("update", false);
        mStartForResult.launch(intent);
    }

}