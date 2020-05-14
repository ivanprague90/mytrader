package com.acme.mytrader.domain.price.source;

import com.acme.mytrader.domain.price.listeners.PriceListener;

public interface PriceSource {
    void addPriceListener(PriceListener listener);
    void removePriceListener(PriceListener listener);
}
