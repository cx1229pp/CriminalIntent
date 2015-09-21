package com.cx.android.criminalintent.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.cx.android.criminalintent.R;

/**
 * Created by ldn on 2015/9/10.
 */
public abstract class BaseActivity extends FragmentActivity{
    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        startFragment();
    }

    private void startFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment crimFragment = fragmentManager.findFragmentById(R.id.flagment_crime);
        if(crimFragment == null){
            crimFragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.flagment_crime,crimFragment)
                    .commit();
        }
    }
}
