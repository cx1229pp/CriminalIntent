package com.cx.android.criminalintent.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.DatePicker;

import com.cx.android.criminalintent.R;
import com.cx.android.criminalintent.tools.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ldn on 2015/9/18.
 */
public class DatePickerFragment extends DialogFragment{
    private Date mCrimeDate;

    public static DatePickerFragment newInstance(Date crimeDate){
        Bundle args = new Bundle();
        args.putSerializable(Constants.EXTRA_CRIME_DATE, crimeDate);

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(args);

        return datePickerFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = View.inflate(getActivity(), R.layout.date_picker, null);
        mCrimeDate = (Date) getArguments().getSerializable(Constants.EXTRA_CRIME_DATE);
        Calendar cal = Calendar.getInstance();
        cal.setTime(mCrimeDate);
        int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH);
        final int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePicker datePicker = (DatePicker) v.findViewById(R.id.dialog_date_picker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCrimeDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
                getArguments().putSerializable(Constants.EXTRA_CRIME_DATE, mCrimeDate);
            }
        });


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Date of crime:")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }

    private void setResult(int resultCode){
        if(getTargetFragment() == null){
            return;
        }

        Intent i = new Intent();
        i.putExtra(Constants.EXTRA_CRIME_DATE,mCrimeDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }
}
