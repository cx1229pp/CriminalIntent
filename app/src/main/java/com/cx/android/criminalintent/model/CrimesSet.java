package com.cx.android.criminalintent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ldn on 2015/9/10.
 */
public class CrimesSet {
    private List<CrimModel> mCrimesList;
    private Context mContext;
    private static CrimesSet sCrimesSet;

    private CrimesSet(Context context){
        mContext = context;
        mCrimesList = new ArrayList<CrimModel>();
        for(int i=0; i < 20; i++){
            CrimModel crim = new CrimModel();
            crim.setmId(UUID.randomUUID().toString());
            crim.setmTitle("Crime" + i);
            crim.setmCrimDate(new Date());
            crim.setmResovle(false);
            mCrimesList.add(crim);
        }
    }

    public static CrimesSet getIntance(Context context){
        if(sCrimesSet == null){
            sCrimesSet = new CrimesSet(context.getApplicationContext());
        }

        return sCrimesSet;
    }

    public List<CrimModel> getmCrimesList(){
        return mCrimesList;
    }

    public CrimModel get(String id){
        if(mCrimesList != null){
            for(CrimModel c : mCrimesList){
                if(c.getmId().equals(id)){
                    return c;
                }
            }
        }

        return null;
    }
}
