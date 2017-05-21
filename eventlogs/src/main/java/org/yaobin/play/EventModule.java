package org.yaobin.play;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.yaobin.play.analysis.IEventAnalyzer;
import org.yaobin.play.analysis.UserPurchaseAnalyzer;
import org.yaobin.play.generator.IEventGenerator;
import org.yaobin.play.generator.RandomEventStreamGenerator;
import org.yaobin.play.service.EventService;

import java.time.Instant;

/**
 * Created by Bin on 5/21/2017.
 */


public class EventModule  extends AbstractModule {


    @Override
    protected void configure() {
        bind( IEventAnalyzer.class).to(UserPurchaseAnalyzer.class);
        bind( IEventGenerator.class).to(RandomEventStreamGenerator.class);
    }


    public static void main(String args[] ){

        Injector injector = Guice.createInjector(new EventModule());
        EventService service = injector.getInstance( EventService.class);
        service.generateAndAnalyz(Instant.now(), 10000, 6, 100 );
    }
}
