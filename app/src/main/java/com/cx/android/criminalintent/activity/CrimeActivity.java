package com.cx.android.criminalintent.activity;


import android.support.v4.app.Fragment;

import com.cx.android.criminalintent.fragment.CrimeFragment;
import com.cx.android.criminalintent.tools.Constants;

public class CrimeActivity extends BaseActivity {

    @Override
    protected Fragment createFragment() {
        String id = getIntent().getStringExtra(Constants.EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(id);
    }
}
