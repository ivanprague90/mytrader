package com.acme.mytrader.domain.price.source.iexcloud;

import com.acme.mytrader.domain.price.listeners.PriceListener;
import com.acme.mytrader.domain.price.source.PriceSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class IexCloudSource implements PriceSource {

    private Map<String, PriceListener> iexCloudListeners = new HashMap<>();

    @Autowired
    private IexCloudClient iexCloudClient;

    @Override
    public void addPriceListener(PriceListener listener) {

        if(!iexCloudListeners.containsKey(listener.getEquitySymbol()))
            iexCloudListeners.put(listener.getEquitySymbol(), listener);
    }

    @Override
    public void removePriceListener(PriceListener listener) {

        iexCloudListeners.remove(listener.getEquitySymbol());
    }

    public void executeListeners(){
        for(Map.Entry<String, PriceListener> single : iexCloudListeners.entrySet()){

            IexCloudResponse response = iexCloudClient.getPrices(single.getKey());

            single.getValue().priceUpdate(response.getSecurity(),response.getPrice());
        }
    }

}
