package org.yaobin.play;

import org.junit.Test;
import org.yaobin.play.data.EventDetail;
import org.yaobin.play.generator.RandomEventStreamGenerator;

import java.time.Instant;
import java.util.HashSet;

import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestEvent {

    @Test
    public void testEventGen(){

        Instant now = Instant.now();
        long durationInMills =  10000L;
        RandomEventStreamGenerator randGen = new RandomEventStreamGenerator();
        List<EventDetail> events = randGen.generateEvents(Instant.now(), durationInMills, 3, 100 );
        Instant end = now.plusMillis( durationInMills);

        assertEquals( events.size(), 100 );
        Set<String> userNames = new HashSet<>();
        events.stream().forEach( event -> { userNames.add( event.userName); assertTrue( event.timeStamp.isBefore( end));});
        assertTrue( userNames.size() <= 3);


    }
}
