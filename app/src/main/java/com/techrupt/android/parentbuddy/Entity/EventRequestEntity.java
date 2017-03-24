package com.techrupt.android.parentbuddy.Entity;
import java.util.Date;


/**
 * Created by ravindersingh on 25/01/17.
 */

public class EventRequestEntity {

    private int EventId;
    private Date StartDate;

    public int getEventID() {
        return EventId;
    }

    public void setEventID(int eventID) {
        EventId = eventID;
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

    private Date EndDate;

    public EventRequestEntity(Date startDate,Date endDate,int eventID)
    {
        EventId=eventID;
        EndDate=endDate;
        StartDate=startDate;
    }

}
