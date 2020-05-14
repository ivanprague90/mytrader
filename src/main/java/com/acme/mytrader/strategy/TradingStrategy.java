package com.acme.mytrader.strategy;

import com.acme.mytrader.domain.price.listeners.IbmListener;
import com.acme.mytrader.domain.price.source.iexcloud.IexCloudSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
@Component
public class TradingStrategy {

    private AtomicInteger count = new AtomicInteger(0);

    @Autowired
    private IexCloudSource iexCloudSource;

    @Scheduled(fixedRate = 10000)
    public void executeListeners() {
        iexCloudSource.executeListeners();
        this.count.incrementAndGet();
    }

    public int getInvocationCount() {
        return this.count.get();
    }
}
