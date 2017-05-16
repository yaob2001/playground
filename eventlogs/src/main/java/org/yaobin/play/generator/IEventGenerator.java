package org.yaobin.play.generator;

import org.yaobin.play.data.EventDetail;

import java.time.Instant;
import java.util.List;

/**
 * Created by Bin on 5/12/2017.
 */
public interface IEventGenerator {
    List<EventDetail> generateEvents(Instant startTime, long durationInMills, int numOfUsers, int numOfEvents);
}
