package org.yaobin.play.data;

import com.google.common.collect.ImmutableList;
import java.time.Instant;
/**
 * Created by Bin on 5/11/2017.
 */
public class EventDetail {

    public enum  EventType {
        SET,
        GET,
        FAILURE,
        REDIRECT,
        PURCHASE;

        private static final ImmutableList<EventType> eventList =  ImmutableList.copyOf( EventType.values());
        private static final int length = eventList.size();

        public static EventType getRandomEventType(){
            return eventList.get( ( (int)(Math.random() * length)) % length);
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
        buf.append( "User: ").append( userName ).append( " Time:").append(timeStamp.toString()).append( " Type:").
                append( eventType.name()).append( " Details:").append( eventValue);
        return buf.toString();
    }
}
