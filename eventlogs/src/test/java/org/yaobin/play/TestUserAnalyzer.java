package org.yaobin.play;

import org.junit.Test;
import org.yaobin.play.analysis.IEventAnalyzer;
import org.yaobin.play.analysis.UserPurchaseAnalyzer;
import org.yaobin.play.data.EventDetail;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Bin on 5/16/2017.
 */
public class TestUserAnalyzer {

    @Test
    public void testAnalyzer(){
        Instant startTime = Instant.now();
        List<EventDetail> input = new LinkedList<>();
        input.add( new EventDetail(
                "user1",
                EventDetail.EventType.GET,
                0.01,
                startTime.plusMillis( 10 )
        ));
        input.add( new EventDetail(
                "user1",
                EventDetail.EventType.PURCHASE,
                0.02,
                startTime.plusMillis( 20 )
        ));

        UserPurchaseAnalyzer analyzer = new UserPurchaseAnalyzer();
        analyzer.analyzeEvent( input );
        Map<String, List<UserPurchaseAnalyzer.PurchaseInfo>> result = analyzer.getUserPurchaseInfo();
        assertEquals( result.keySet().size(), 1);
        assertEquals( result.values().size(), 1);

        input.clear();
        input.add( new EventDetail(
                "user1",
                EventDetail.EventType.PURCHASE,
                0.02,
                startTime.plusMillis( 20 )
        ));
        analyzer = new UserPurchaseAnalyzer();
        analyzer.analyzeEvent( input );
        result = analyzer.getUserPurchaseInfo();
        assertEquals( result.keySet().size(), 1);
        assertEquals( result.values().size(), 1);

    }
}
