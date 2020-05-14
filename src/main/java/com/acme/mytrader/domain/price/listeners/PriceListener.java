package com.acme.mytrader.domain.price.listeners;

public interface PriceListener {
    void priceUpdate(String security, double price);

    String getEquitySymbol();
}
