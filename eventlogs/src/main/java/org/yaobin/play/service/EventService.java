package org.yaobin.play.service;

import com.google.inject.Inject;
import org.yaobin.play.analysis.IEventAnalyzer;
import org.yaobin.play.data.EventDetail;
import org.yaobin.play.generator.IEventGenerator;

import java.time.Instant;
import java.util.List;

/**
 * Created by Bin on 5/21/2017.
 */
public class EventService implements IEventService {

    private IEventAnalyzer analyzer;
    private IEventGenerator generator;

    @Inject
    public EventService(IEventGenerator generator, IEventAnalyzer analyzer){
        this.generator= generator;
        this.analyzer= analyzer;
    }

    @Override
    public void generateAndAnalyz(Instant startTime, long durationInMills, int numOfUsers, int numOfEvents) {
        List<EventDetail> events = generator.generateEvents( startTime,  durationInMills,  numOfUsers,  numOfEvents);
        analyzer.analyzeEvent(events);
    }

}
