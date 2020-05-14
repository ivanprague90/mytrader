package com.acme.mytrader.domain.price.source.iexcloud;

import org.springframework.stereotype.Service;
import pl.zankowski.iextrading4j.api.stocks.Quote;
import pl.zankowski.iextrading4j.client.IEXCloudClient;
import pl.zankowski.iextrading4j.client.IEXCloudTokenBuilder;
import pl.zankowski.iextrading4j.client.IEXTradingApiVersion;
import pl.zankowski.iextrading4j.client.IEXTradingClient;
import pl.zankowski.iextrading4j.client.rest.request.stocks.QuoteRequestBuilder;

@Service
public class IexCloudClient {
private String publicToken = "Tpk_18dfe6cebb4f41ffb219b9680f9acaf2";
private String secretToken = "Tsk_3eedff6f5c284e1a8b9bc16c54dd1af3";

    protected IexCloudResponse getPrices(String symbol) {

        Quote quote = callIexCloud(symbol);

        if(quote == null || quote.getLatestPrice() == null || quote.getPrimaryExchange() == null)
            throw new IexCloudException("No information from IEX Cloud");

        IexCloudResponse response = new IexCloudResponse();
        response.setPrice(quote.getLatestPrice().doubleValue());
        response.setSecurity(quote.getPrimaryExchange());

        return response;
    }

    public Quote callIexCloud(String symbol){
        final IEXCloudClient cloudClient = IEXTradingClient.create(IEXTradingApiVersion.IEX_CLOUD_V1_SANDBOX,
                new IEXCloudTokenBuilder()
                        .withPublishableToken(publicToken)
                        .withSecretToken(secretToken)
                        .build());
        final Quote quote = cloudClient.executeRequest(new QuoteRequestBuilder()
                .withSymbol(symbol)
                .build());

        return quote;
    }
}
