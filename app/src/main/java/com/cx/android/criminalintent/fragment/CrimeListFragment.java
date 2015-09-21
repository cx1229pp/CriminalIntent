package com.cx.android.criminalintent.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.cx.android.criminalintent.R;
import com.cx.android.criminalintent.activity.CrimeViewPagerAcitvity;
import com.cx.android.criminalintent.adapter.CrimeAdapter;
import com.cx.android.criminalintent.model.CrimModel;
import com.cx.android.criminalintent.model.CrimesSet;
import com.cx.android.criminalintent.tools.Constants;

import java.util.List;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class CrimeListFragment extends ListFragment {
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.title_activity_crime_list);

        init();
    }

    private void init(){
        mContext = getActivity();
        List<CrimModel> list = CrimesSet.getIntance(mContext).getmCrimesList();
        CrimeAdapter crimeAdapter = new CrimeAdapter(mContext,R.layout.crime_list_item,list);
        setListAdapter(crimeAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        CrimModel crimModel = (CrimModel) getListAdapter().getItem(position);

        Intent crimeActivityIntent = new Intent(mContext,CrimeViewPagerAcitvity.class);
        crimeActivityIntent.putExtra(Constants.EXTRA_CRIME_ID,crimModel.getmId());
        startActivity(crimeActivityIntent);
    }
}
