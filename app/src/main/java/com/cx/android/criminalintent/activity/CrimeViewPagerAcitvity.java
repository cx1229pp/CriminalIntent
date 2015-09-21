package com.cx.android.criminalintent.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.cx.android.criminalintent.R;
import com.cx.android.criminalintent.fragment.CrimeFragment;
import com.cx.android.criminalintent.model.CrimModel;
import com.cx.android.criminalintent.model.CrimesSet;
import com.cx.android.criminalintent.tools.Constants;

import java.util.List;

/**
 * Created by ldn on 2015/9/16.
 */
public class CrimeViewPagerAcitvity extends FragmentActivity{
    private ViewPager mViewPager;
    private CrimesSet mCrimeSet;
    private List<CrimModel> mCrimeSetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        init();
    }

    private  void init(){
        mCrimeSet = CrimesSet.getIntance(this);
        mCrimeSetList = mCrimeSet.getmCrimesList();
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                CrimModel c = mCrimeSetList.get(position);
                return CrimeFragment.newInstance(c.getmId());
            }

            @Override
            public int getCount() {
                return mCrimeSetList.size();
            }
        });

        String currentItemId = getIntent().getStringExtra(Constants.EXTRA_CRIME_ID);
        int length = mCrimeSetList.size();
        for(int i=0; i<length; i++){
            if(currentItemId.equals(mCrimeSetList.get(i).getmId())){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                CrimModel c = mCrimeSetList.get(state);
                setTitle(c.getmTitle());
            }
        });
    }
}
