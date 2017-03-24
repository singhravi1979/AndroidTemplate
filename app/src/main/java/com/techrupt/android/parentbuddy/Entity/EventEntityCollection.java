package com.techrupt.android.parentbuddy.Entity;

import com.techrupt.android.parentbuddy.Entity.EventEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ravindersingh on 02/02/17.
 */

public class EventEntityCollection implements Serializable {

    public List<EventEntity> getEvents() {
        return Events;
    }

    public void setEvents(List<EventEntity> events) {
        Events = events;
    }

    private List<EventEntity> Events;

}


