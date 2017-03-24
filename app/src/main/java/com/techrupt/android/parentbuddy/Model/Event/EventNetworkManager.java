package com.techrupt.android.parentbuddy.Model.Event;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.techrupt.android.parentbuddy.Entity.EventEntity;
import com.techrupt.android.parentbuddy.Entity.EventRequestEntity;
import com.techrupt.android.parentbuddy.Model.NetworkManager;
import com.techrupt.android.parentbuddy.common.DateDeserializer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ravindersingh on 17/01/17.
 */

public class EventNetworkManager extends NetworkManager<EventEntity> {


    public EventNetworkManager(String baseurl) {
        super(baseurl);
    }

    public List<EventEntity> GetAllEventsByStartandEndDate(Date startdate,Date endDate) throws IOException, JSONException {
        EventRequestEntity requestEntity=new EventRequestEntity(startdate,endDate,0);
        Gson serializer=new Gson();
       String requestdata= serializer.toJson(requestEntity);
        String urlaction=buildurlString("GetAllEvents");
       return GetListofItems(urlaction,"POST",requestdata);

    }

    @Override
    public List<EventEntity> parseItems(JSONObject jsonobject) throws JSONException {
       List<EventEntity> entityList=new ArrayList<EventEntity>();
        JSONArray dataarray=jsonobject.getJSONArray("Events");

        GsonBuilder gsonBuilder=new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        Gson seriaiizer=gsonBuilder.create();
        for (int i = 0; i < dataarray.length(); i++)
        {
           JSONObject dataobject=dataarray.getJSONObject(i);

            EventEntity entity=new EventEntity();


            entity= seriaiizer.fromJson(dataobject.toString(),EventEntity.class);
//            entity.setEventId(dataobject.getInt("EventId"));
//            entity.setStartDate((Date) dataobject.get("StartDate"));
//            entity.setEndDate((Date) dataobject.get("EndDate"));
//            entity.setEventDescription(dataobject.getString("EventDescription"));
//            entity.setEventName(dataobject.getString("EventName"));
//           LocationEntity locentity=new LocationEntity();
//            locentity.setLongitude(dataobject.getDouble("Latitude"));
//            locentity.setLongitude(dataobject.getDouble("Longitude"));
//            entity.setLocationEntity(locentity);
            entityList.add(entity);
        }

        return entityList;
    }

    @Override
    public String buildurlString(String operatioName) {
        String url = Uri.parse(getBaseUrl())
                .buildUpon()
                .appendPath("Event")
                .appendPath("GetAllEventsByStartandEndDate")
                .build().toString();

        return url;
    }
}
