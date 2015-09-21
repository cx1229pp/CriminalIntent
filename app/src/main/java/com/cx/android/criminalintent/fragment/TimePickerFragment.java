package com.cx.android.criminalintent.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TimePicker;

import com.cx.android.criminalintent.R;
import com.cx.android.criminalintent.tools.Constants;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ldn on 2015/9/20.
 */
public class TimePickerFragment extends DialogFragment {
    private Date mCrimDate;

    public static TimePickerFragment newInstance(Date date){
        Bundle args = new Bundle();
        args.putSerializable(Constants.EXTRA_CRIME_TIME,date);

        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setArguments(args);

        return timePickerFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mCrimDate = (Date) getArguments().getSerializable(Constants.EXTRA_CRIME_TIME);
        View v = View.inflate(getActivity(), R.layout.time_picker,null);
        TimePicker timePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mCrimDate = new GregorianCalendar(0, 0, 0, hourOfDay, minute).getTime();
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("Date of crime:")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onResult(Activity.RESULT_OK);
                    }
                })
                .create();
    }

    private void onResult(int resultCode){
        if(getTargetFragment() == null) return;

        Intent i = new Intent();
        i.putExtra(Constants.EXTRA_CRIME_TIME,mCrimDate);

        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }
}
