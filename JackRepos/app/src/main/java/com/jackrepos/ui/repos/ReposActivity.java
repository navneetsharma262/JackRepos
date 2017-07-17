package com.jackrepos.ui.repos;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jackrepos.R;
/**
 * Created by Navneet on 13-07-2017.
 */
public class ReposActivity extends AppCompatActivity {
    private boolean mDoublePressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
          Adding ReposFragment to activity
        */
        ReposFragment repoFragment = new ReposFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment_container,  repoFragment, "repoFragment").addToBackStack("repoFragment").commit();
    }

    /*
          Handle phone back button action
        */

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        } else if (mDoublePressed) {
            finish();
        } else {
            mDoublePressed = true;
            Toast.makeText(ReposActivity.this,"Press back button again to exit",Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDoublePressed = false;
                }
            }, 1500);
        }
    }
}
