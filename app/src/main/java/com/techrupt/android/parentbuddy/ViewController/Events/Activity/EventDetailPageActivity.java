package com.techrupt.android.parentbuddy.ViewController.Events.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.techrupt.android.parentbuddy.Entity.EventEntity;
import com.techrupt.android.parentbuddy.Entity.EventEntityCollection;
import com.techrupt.android.parentbuddy.R;
import com.techrupt.android.parentbuddy.ViewController.Events.Fragment.EventFragment;

import java.util.List;

public class EventDetailPageActivity extends FragmentActivity implements EventFragment.OnFragmentInteractionListener {

    private static final String EVENT_LIST_KEY="com.techrupt.android.parentbuddy.ViewController.Events.Activity.EventDetailPageActivity.eventlist";
    private ViewPager mEventViewPager;

    private List<EventEntity> mEventEntityList;


    public static Intent newInstance(Context context, List<EventEntity> eventEntityList)
    {
        Intent eventDetailIntent=new Intent(context,EventDetailPageActivity.class);
        EventEntityCollection collection=new EventEntityCollection();
        collection.setEvents(eventEntityList);
        eventDetailIntent.putExtra(EVENT_LIST_KEY,collection);
        return eventDetailIntent;

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail_page);

        mEventEntityList=((EventEntityCollection)getIntent().getSerializableExtra(EVENT_LIST_KEY)).getEvents();
        mEventViewPager=(ViewPager) findViewById(R.id.activity_event_detail_page);
        mEventViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return EventFragment.newInstance(mEventEntityList.get(position));
            }

            @Override
            public int getCount() {
                return mEventEntityList.size();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }



}
