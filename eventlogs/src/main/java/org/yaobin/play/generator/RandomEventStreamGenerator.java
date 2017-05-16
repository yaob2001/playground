package org.yaobin.play.generator;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaobin.play.data.EventDetail;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bin on 5/11/2017.
 */
public class RandomEventStreamGenerator implements IEventGenerator{

    private static Logger log = LoggerFactory.getLogger( RandomEventStreamGenerator.class);
    public RandomEventStreamGenerator(){}

    @Override
    public List<EventDetail> generateEvents(Instant startTime, long durationInMills, int numOfUsers, int numOfEvents){

        List<String> userNames = new ArrayList();
        for( int i=0; i<numOfUsers; ++i ){
            userNames.add( "uname" + i );
        }

        List<EventDetail> events = new ArrayList<>( numOfEvents);
        long offset = 0;
        for( int i = 0; i< numOfEvents; ++i ){
            //String user, EventType type, String detail, Instant timeInMillis;
            String userName = userNames.get( ((int)(Math.random()*numOfUsers)) % numOfUsers );

            EventDetail.EventType eventType = EventDetail.EventType.getRandomEventType();
            offset = offset +  (int)( durationInMills / numOfEvents * Math.random());
            EventDetail event = new EventDetail(
                    userName,
                    eventType,
                    Math.random(),
                    startTime.plusMillis(offset) );
            events.add( event );
        }
        return events;
    }

}
