package com.techrupt.android.parentbuddy.ViewController.Events.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.techrupt.android.parentbuddy.Entity.EventEntity;
import com.techrupt.android.parentbuddy.Model.Event.EventNetworkManager;
import com.techrupt.android.parentbuddy.R;
import com.techrupt.android.parentbuddy.ViewController.Events.Activity.EventDetailPageActivity;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ravindersingh on 29/01/17.
 */

public class EventActivityListFragment extends Fragment {
    private static final String EXTRA_START_DATE="com.techrupt.android.parentbuddy.ViewController.Events.Fragment.EventActivityListFragment.StartDate";
    private static final String EXTRA_END_DATE="com.techrupt.android.parentbuddy.ViewController.Events.Fragment.EventActivityListFragment.EndDate";

    private EventNetworkManager mEventNetworkManager;

    public EventNetworkManager getEventNetworkManager() {
        return mEventNetworkManager;
    }


    public void setEventNetworkManager(EventNetworkManager eventNetworkManager) {
        mEventNetworkManager = eventNetworkManager;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date startDate) {
        mStartDate = startDate;
    }

    public Date getEndDate() {
        return mEndDate;
    }

    public void setEndDate(Date endDate) {
        mEndDate = endDate;
    }

    private Date mStartDate;
    private Date mEndDate;
    private RecyclerView mEventListRecyclerView;
    private EventAdapter mEventAdapter;
    private static SimpleDateFormat mSimpleDateFormat;
    private List<EventEntity> mEventEntities;
    public static EventActivityListFragment newInstance( Date startDate, Date endDate)
    {
        Bundle bundle=new Bundle();
        EventActivityListFragment activityListFragment=new EventActivityListFragment();
        mSimpleDateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        bundle.putSerializable (EXTRA_START_DATE,mSimpleDateFormat.format(startDate));
        bundle.putSerializable(EXTRA_END_DATE,mSimpleDateFormat.format(endDate));
         activityListFragment.setArguments(bundle);

        return activityListFragment;


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.activity_all_events,container,false);

        mEventNetworkManager=new EventNetworkManager(getString( R.string.baseurl));





        mEventListRecyclerView=(RecyclerView) view.findViewById(R.id.recycler_view_events);
        mEventListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        new FetchItemsTask().execute();

        return  view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mStartDate=mSimpleDateFormat.parse(getArguments().getSerializable(EXTRA_START_DATE).toString());
            mEndDate=mSimpleDateFormat.parse(getArguments().getSerializable(EXTRA_END_DATE).toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }





    private void setupAdapter()
    {
        if(mEventAdapter==null) {
            mEventAdapter = new EventAdapter(mEventEntities);
            mEventListRecyclerView.setAdapter(mEventAdapter);
        }else{
            //mEventAdapter.setEventEntities(mEventEntities);
            mEventAdapter.notifyDataSetChanged();
        }

    }



    private class FetchItemsTask extends AsyncTask<Void,Void,List<EventEntity>>
    {


        @Override
        protected List<EventEntity> doInBackground(Void... params) {

            EventNetworkManager eventNetworkManager=new EventNetworkManager(getString(R.string.baseurl));
            try {
                return eventNetworkManager.GetAllEventsByStartandEndDate(mStartDate,mEndDate);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(List<EventEntity> items) {
            mEventEntities=items;
            setupAdapter();
        }
    }
    private class EventHolder extends  RecyclerView.ViewHolder implements View.OnClickListener
    {

        private TextView mtxtEventName;
        private TextView mtxtEventDescription;
        private TextView mtxtEventStartDate;
        private TextView mtxtEventEndDate;
        private RatingBar mEventRatingbar;
        public EventEntity getEventEntity() {
            return mEventEntity;
        }

        public void setEventEntity(EventEntity eventEntity) {
            mEventEntity = eventEntity;
        }

        private EventEntity mEventEntity;

        public EventHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mtxtEventDescription = (TextView) itemView.findViewById(R.id.txtEventDescription);
            mtxtEventName = (TextView) itemView.findViewById(R.id.txtEventName);
            mtxtEventStartDate = (TextView) itemView.findViewById(R.id.txtEventStartDate);
            mtxtEventEndDate = (TextView) itemView.findViewById(R.id.txtEventEndDate);
            mEventRatingbar = (RatingBar) itemView.findViewById(R.id.ratingBar);
        }


        public void BindEvent(EventEntity eventEntity)
        {
            mEventEntity=eventEntity;
            mtxtEventDescription.setText(mEventEntity.getEventDescription());
            mtxtEventName.setText(mEventEntity.getEventName());
            mtxtEventStartDate.setText(mEventEntity.getStartDate().toString());
            mtxtEventEndDate.setText(mEventEntity.getEndDate().toString());

            mEventRatingbar.setRating(mEventEntity.getEventRating());
        }

        @Override
        public void onClick(View v) {
            Intent intent= EventDetailPageActivity.newInstance(getActivity(),mEventEntities);

            startActivity(intent);
        }
    }

    private class EventAdapter extends RecyclerView.Adapter<EventHolder>
    {
        public EventAdapter(List<EventEntity> eventEntityList)
        {
            mEventEntities=eventEntityList;
        }

        @Override
        public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=null;
            view = layoutInflater.inflate(R.layout.activity_event, parent, false);
            return new EventHolder(view);
        }

        @Override
        public void onBindViewHolder(EventHolder holder, int position) {

            EventEntity eventEntity= mEventEntities.get(position);
            holder.BindEvent(eventEntity);
        }

        @Override
        public int getItemCount() {
            return mEventEntities== null?0:mEventEntities.size();
        }
    }



}
