package com.techrupt.android.parentbuddy.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ravindersingh on 17/01/17.
 */

public class EventEntity implements Serializable {

    private int EventType;

    private String EventDescription;

    private Date StartDate;

    private Date EndDate;

    private int EventId;

    private String EventName;

    public int getEventRating() {
        return EventRating;
    }

    public void setEventRating(int eventRating) {
        EventRating = eventRating;
    }

    private int EventRating;

    private double Longitude;
    private double Latitude;
    public int getEventType() {
        return EventType;
    }

    public void setEventType(int eventType) {
        EventType = eventType;
    }

    public String getEventDescription() {
        return EventDescription;
    }

    public void setEventDescription(String eventDescription) {
        EventDescription = eventDescription;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }



    public int getEventId() {
        return EventId;
    }

    public void setEventId(int eventId) {
        EventId = eventId;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }



    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        this.Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        this.Latitude = latitude;
    }




}

