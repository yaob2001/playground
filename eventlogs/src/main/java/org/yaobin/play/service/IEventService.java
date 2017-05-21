package org.yaobin.play.service;

import java.time.Instant;

/**
 * Created by Bin on 5/21/2017.
 */
public interface IEventService {
    void generateAndAnalyz(Instant startTime, long durationInMills, int numOfUsers, int numOfEvents);
}
