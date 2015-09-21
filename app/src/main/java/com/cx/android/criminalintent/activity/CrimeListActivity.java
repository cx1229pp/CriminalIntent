package com.cx.android.criminalintent.activity;


import android.support.v4.app.Fragment;

import com.cx.android.criminalintent.fragment.CrimeListFragment;

public class CrimeListActivity extends BaseActivity {


    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
