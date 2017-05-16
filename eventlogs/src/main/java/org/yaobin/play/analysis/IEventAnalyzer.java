package org.yaobin.play.analysis;

import org.yaobin.play.data.EventDetail;

import java.util.List;

/**
 * Created by Bin on 5/16/2017.
 */
public interface IEventAnalyzer {
    void analyzeEvent(List<EventDetail> events );

}
