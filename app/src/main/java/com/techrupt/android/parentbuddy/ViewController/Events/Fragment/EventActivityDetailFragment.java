package com.techrupt.android.parentbuddy.ViewController.Events.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techrupt.android.parentbuddy.R;

/**
 * Created by ravindersingh on 30/01/17.
 */

public class EventActivityDetailFragment extends Fragment {

    private static final String EVENT_ID_KEY="com.techrupt.android.parentbuddy.ViewController.Events.Fragment.EventActivityDetailFragment.EventId";
    private int mEventId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mEventId= (int) getArguments().getSerializable(EVENT_ID_KEY);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_event_detail_page,container,false);

        return view;
    }

    public static EventActivityDetailFragment newInstance(int eventId)
    {
        Bundle args=new Bundle();
        args.putSerializable(EVENT_ID_KEY,eventId);
        EventActivityDetailFragment eventActivityDetailFragment=new EventActivityDetailFragment();
        eventActivityDetailFragment.setArguments(args);
        return eventActivityDetailFragment;
    }
}
