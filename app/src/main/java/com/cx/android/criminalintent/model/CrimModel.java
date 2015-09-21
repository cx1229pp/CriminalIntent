package com.cx.android.criminalintent.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by ldn on 2015/9/8.
 */
public class CrimModel {
    private String mTitle;
    private String mId;
    private Date mCrimDate;
    private boolean mResovle;

    public void setmCrimDate(Date mCrimDate) {
        this.mCrimDate = mCrimDate;
    }

    public void setmResovle(boolean mResovle) {
        this.mResovle = mResovle;
    }

    public Date getmCrimDate() {

        return mCrimDate;
    }

    public boolean ismResovle() {
        return mResovle;
    }

    public CrimModel(){
        mId = UUID.randomUUID().toString();
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmId() {
        return mId;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    @Override
    public String toString() {
        return mTitle;
    }

    public String getFormatDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(this.getmCrimDate());
    }

    public String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(this.getmCrimDate());
    }
}
