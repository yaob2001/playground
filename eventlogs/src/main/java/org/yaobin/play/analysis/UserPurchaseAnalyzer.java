package org.yaobin.play.analysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaobin.play.data.EventDetail;
import org.yaobin.play.generator.IEventGenerator;
import org.yaobin.play.generator.RandomEventStreamGenerator;

import java.time.Instant;
import java.util.*;


/**
    track the user sessions, get all the get events between purchases
 */
public class UserPurchaseAnalyzer implements IEventAnalyzer{

    public static class PurchaseInfo {

        Instant purchaseTime; //time of purchase
        List<Instant> priorGets; //time of prior get

        public PurchaseInfo( Instant purchaseTime, List<Instant> priorGets){
            this.purchaseTime = purchaseTime;
            this.priorGets = priorGets;
        }

        public String toString(){
            StringBuffer buf = new StringBuffer();
            buf.append( "purchase time:" ).append( purchaseTime );
            priorGets.forEach(
                    inst -> buf.append(" GET time:").append( inst)
            );
            return buf.toString();
        }
    }

    private static Logger log = LoggerFactory.getLogger( UserPurchaseAnalyzer.class);

    public Map<String, List<PurchaseInfo>> getUserPurchaseInfo() {
        return userPurchaseInfo;
    }

    private Map<String, List<PurchaseInfo>> userPurchaseInfo;

    public UserPurchaseAnalyzer(){
        userPurchaseInfo =  new HashMap<>();
    }



    @Override
    public void analyzeEvent(List<EventDetail> events) {

        Map<String, List<Instant>> getEventCache = new HashMap();
        events.stream().forEach( event -> {
            if( event.eventType == EventDetail.EventType.GET){
                getEventCache.putIfAbsent( event.userName, new LinkedList<Instant>());
                getEventCache.get( event.userName).add( event.timeStamp);
            }

            if( event.eventType == EventDetail.EventType.PURCHASE ){
                PurchaseInfo pInfo;
                if( getEventCache.containsKey( event.userName))
                    pInfo = new PurchaseInfo(event.timeStamp, getEventCache.remove(event.userName));
                else
                    pInfo = new PurchaseInfo(event.timeStamp, new LinkedList<>());

                userPurchaseInfo.putIfAbsent(event.userName, new LinkedList());
                userPurchaseInfo.get( event.userName).add( pInfo);
            }
        });

        userPurchaseInfo.entrySet().stream().forEach(
                ( userEntry) -> {
                    log.info("purchase by {}", userEntry.getKey());
                    userEntry.getValue().stream().forEach( pInfo -> log.info( pInfo.toString()));
                }
        );
    }

    public static void main( String []args ){
        IEventGenerator generator = new RandomEventStreamGenerator();
        List< EventDetail> events = generator.generateEvents( Instant.now(),  1000,  5, 1000);
        IEventAnalyzer analyzer = new UserPurchaseAnalyzer();
        analyzer.analyzeEvent( events );
    }
}
