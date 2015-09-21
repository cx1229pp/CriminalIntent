package com.cx.android.criminalintent.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cx.android.criminalintent.R;
import com.cx.android.criminalintent.model.CrimModel;

import java.util.List;

/**
 * Created by ldn on 2015/9/10.
 */
public class CrimeAdapter extends ArrayAdapter {
    private List<CrimModel> mCrimesList;
    private Context mContext;

    public CrimeAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        mContext = context;
        mCrimesList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CrimModel crime = mCrimesList.get(position);
        View view;
        ViewHandler viewHandler;
        if(convertView == null){
            viewHandler = new ViewHandler();
            view = View.inflate(mContext, R.layout.crime_list_item,null);
            viewHandler.mTitleTextView = (TextView) view.findViewById(R.id.tv_title);
            viewHandler.mCheckBox = (CheckBox) view.findViewById(R.id.cb_t);
            viewHandler.setText(crime.getmTitle());

            view.setTag(viewHandler);
        }else{
            view = convertView;
            viewHandler = (ViewHandler) view.getTag();
            viewHandler.setText(crime.getmTitle());
        }

        return view;
    }


    private class ViewHandler{
        TextView mTitleTextView;
        CheckBox mCheckBox;

        public void setText(String title){
            mTitleTextView.setText(title);
        }
    }
}
