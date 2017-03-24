package com.techrupt.android.parentbuddy.ViewController.Events.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.techrupt.android.parentbuddy.ViewController.Events.Fragment.EventActivityListFragment;
import com.techrupt.android.parentbuddy.ViewController.SingleFragmentActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventActivityList extends SingleFragmentActivity {

    private static final String EXTRA_START_DATE="com.techrupt.android.parentbuddy.ViewController.Events.Activity.EventActivityList.StartDate";
    private static final String EXTRA_End_DATE="com.techrupt.android.parentbuddy.ViewController.Events.Activity.EventActivityList.EndDate";
    private Date mStartDate;

    private Date mEndDate;
    private static SimpleDateFormat mDateFormat;
    @Override
    protected Fragment createFragment() {
        try {
            mStartDate=mDateFormat.parse(getIntent().getSerializableExtra(EXTRA_START_DATE).toString());
            mEndDate= mDateFormat.parse(getIntent().getSerializableExtra(EXTRA_End_DATE).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return EventActivityListFragment.newInstance(mStartDate,mEndDate);
    }

    public static Intent newInstance(Context context,Date fromdate,Date endDate)
    {
        mDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Intent eventIntent=new Intent(context,EventActivityList.class);

        eventIntent.putExtra(EXTRA_START_DATE,mDateFormat.format(fromdate));
        eventIntent.putExtra(EXTRA_End_DATE,mDateFormat.format(endDate));

        return eventIntent;


    }
}
