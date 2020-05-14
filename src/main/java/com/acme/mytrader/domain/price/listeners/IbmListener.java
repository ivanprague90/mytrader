package com.acme.mytrader.domain.price.listeners;

import com.acme.mytrader.domain.execution.ExecutionServiceImpl;
import com.acme.mytrader.domain.price.source.iexcloud.IexCloudSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class IbmListener implements PriceListener {

    private int volume = 50;
    private float priceThreshold;

    @Autowired
    private IexCloudSource iexCloudSource;

    public IbmListener(int priceThreshold) {
        this.priceThreshold = priceThreshold;
    }

    @Override
    public void priceUpdate(String security, double price) {

        if (price <= priceThreshold) {
            new ExecutionServiceImpl().buy(security, price, volume);
        }
    }

    @Override
    public String getEquitySymbol() {
        return "IBM";
    }
}
