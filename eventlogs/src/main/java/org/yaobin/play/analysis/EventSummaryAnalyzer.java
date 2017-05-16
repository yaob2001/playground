package org.yaobin.play.analysis;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaobin.play.data.EventDetail;
import org.yaobin.play.generator.IEventGenerator;
import org.yaobin.play.generator.RandomEventStreamGenerator;

import java.time.Instant;
import java.util.List;


/**
 * Created by Bin on 5/12/2017.
 */
public class EventSummaryAnalyzer implements IEventAnalyzer {

    private static Logger log = LoggerFactory.getLogger( EventSummaryAnalyzer.class);
    SummaryStatistics valueSummary;
    SummaryStatistics timeStampSummary;

    public EventSummaryAnalyzer(){
        valueSummary = new SummaryStatistics();
        timeStampSummary = new SummaryStatistics();
    }

    @Override
    public void analyzeEvent(List<EventDetail> events ){
        events.stream().forEach(e -> {
            valueSummary.addValue(e.eventValue);
            timeStampSummary.addValue( e.timeStamp.toEpochMilli());
        });
        log.info( "event value summary statistics {}",valueSummary.toString());
        log.info( "event timestamp summary statistics {}", timeStampSummary.toString());
    }

    public static void main( String[] args) {

        IEventGenerator generator = new RandomEventStreamGenerator();
        List< EventDetail> events = generator.generateEvents( Instant.now(),  1000,  5, 1000);
        IEventAnalyzer analyzer = new EventSummaryAnalyzer();
        analyzer.analyzeEvent( events );

    }
}
