package com.cx.android.criminalintent.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.cx.android.criminalintent.R;
import com.cx.android.criminalintent.model.CrimModel;
import com.cx.android.criminalintent.model.CrimesSet;
import com.cx.android.criminalintent.tools.Constants;

import java.util.Date;


/**
 *
 */
public class CrimeFragment extends Fragment {
    private EditText mEditTextInput;
    private CrimModel mCrimModel;
    private Button mCrimDateButton;
    private Button mCrimTimeButton;
    private CheckBox mCrimCheckBox;
    private static final String DIALOG_DATE = "date";
    private static final String DIALOG_TIME = "time";
    private static final int RESULT_DATE = 0;
    private static final int RESULT_TIME = 1;

    public static CrimeFragment newInstance(String id){
        Bundle args = new Bundle();
        args.putString(Constants.EXTRA_CRIME_ID, id);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mEditTextInput = (EditText) v.findViewById(R.id.et_input);
        String crimeId = getArguments().getString(Constants.EXTRA_CRIME_ID);
        mCrimModel = CrimesSet.getIntance(getActivity()).get(crimeId);
        mEditTextInput.setText(mCrimModel.getmTitle());

        mCrimDateButton = (Button) v.findViewById(R.id.bt_crimedate);
        mCrimDateButton.setText(mCrimModel.getFormatDate());

        mCrimTimeButton = (Button) v.findViewById(R.id.bt_crimetime);
        mCrimTimeButton.setText(mCrimModel.getTime());

        mCrimCheckBox = (CheckBox) v.findViewById(R.id.cb_crimeresolve);
        mCrimCheckBox.setChecked(mCrimModel.ismResovle());

        titleTextClick();
        setmCrimDateButtonClick();
        setTimeButtonClick();

        return v;
    }

    private void titleTextClick() {
        mEditTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrimModel.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setmCrimDateButtonClick(){
        mCrimDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mCrimModel.getmCrimDate());
                datePickerFragment.setTargetFragment(CrimeFragment.this, RESULT_DATE);
                datePickerFragment.show(fm, DIALOG_DATE);
            }
        });
    }

    private void setTimeButtonClick(){
        mCrimTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment timePickerFragment = TimePickerFragment.newInstance(mCrimModel.getmCrimDate());
                timePickerFragment.setTargetFragment(CrimeFragment.this,RESULT_TIME);
                timePickerFragment.show(fm,DIALOG_TIME);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != Activity.RESULT_OK)return;

        if(requestCode == RESULT_DATE){
            Date date = (Date) data.getSerializableExtra(Constants.EXTRA_CRIME_DATE);
            mCrimModel.setmCrimDate(date);

            mCrimDateButton.setText(mCrimModel.getFormatDate());
        }else if(requestCode == RESULT_TIME){
            Date date = (Date) data.getSerializableExtra(Constants.EXTRA_CRIME_TIME);
            mCrimModel.setmCrimDate(date);

            mCrimTimeButton.setText(mCrimModel.getTime());
        }
    }
}
