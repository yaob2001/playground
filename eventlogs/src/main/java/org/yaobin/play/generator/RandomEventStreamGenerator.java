package org.yaobin.play.generator;



import org.yaobin.play.data.EventDetail;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bin on 5/11/2017.
 */
public class RandomEventStreamGenerator implements IEventGenerator{

    public RandomEventStreamGenerator(){}

    public List<EventDetail> generateEvents(Instant startTime, long durationInMills, int numOfUsers, int numOfEvents){

        List<String> userNames = new ArrayList();
        for( int i=0; i<numOfUsers; ++i ){
            userNames.add( "uname" + i );
        }

        List<EventDetail> events = new ArrayList<>( numOfEvents);
        for( int i = 0; i< numOfEvents; ++i ){
            //String user, EventType type, String detail, Instant timeInMillis;
            String userName = userNames.get( i%numOfUsers);
            EventDetail.EventType eventType = EventDetail.EventType.getRandomEventType(i);
            events.add(
                    new EventDetail(
                            userName,
                            eventType,
                            Math.random(),
                            startTime.plusMillis((long) (durationInMills / numOfEvents * Math.random()*i)))
            );

        }
        return events;
    }

}
