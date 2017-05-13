package org.yaobin.play.data;



import com.google.common.collect.ImmutableList;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;


/**
 * Created by Bin on 5/11/2017.
 */
public class EventDetail {
    private static DateTimeFormatter formatter =  RFC_1123_DATE_TIME;
    public enum  EventType {
        SET,
        GET,
        FAILURE,
        REDIRECT,
        PURCHASE;

        private static final ImmutableList<EventType> eventList =  ImmutableList.copyOf( EventType.values());
        private static final int length = eventList.size();

        public static EventType getRandomEventType(int seed){
            return eventList.get(seed%length);
        }
    }

    public EventType eventType;
    public Double eventValue;
    public Instant timeStamp;
    public String userName;

    public EventDetail(String user, EventType type, Double value, Instant timeInMillis){
        userName = user;
        eventType = type;
        eventValue = value;
        timeStamp =  timeInMillis;
    }

    public String toString(){
        StringBuffer buf = new StringBuffer();
        buf.append( "Time:").append(formatter.format(timeStamp)).append( " Type:").
                append( eventType.name()).append( " Details:").append( eventValue);
        return buf.toString();
    }
}
