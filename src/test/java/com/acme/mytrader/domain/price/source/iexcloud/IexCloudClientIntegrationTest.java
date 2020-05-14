package com.acme.mytrader.domain.price.source.iexcloud;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.zankowski.iextrading4j.api.stocks.Quote;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class IexCloudClientIntegrationTest {

    @Spy
    private IexCloudClient classUnderTest;

    @Test
    @DisplayName("getPrices return full quote when Iex Cloud reply correctly")
    void getPrices_whenIexCloudReplyCorrectly_thenQuoteNotNull () {

        String security = "sec";
        BigDecimal price = new BigDecimal(100);

        Quote quote = new Quote(null,null,security,null,null,null,null,null,null,null,null,null,price,null,null,null,null,null,null,null,null,null,null,null,null,
                null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);

        Mockito.doReturn(quote).when(classUnderTest).callIexCloud(anyString());

        IexCloudResponse response = new IexCloudResponse();
        response.setSecurity(security);
        response.setPrice(price.doubleValue());

        assertThat(classUnderTest.getPrices("").getSecurity()).isEqualTo(response.getSecurity());
    }

    @Test
    @DisplayName("getPrices return IexCloudException when Iex Cloud doesn't reply")
    void getPrices_whenIexCloudDoesntReply_thenIexCloudException () {

        Mockito.doReturn(null).when(classUnderTest).callIexCloud(anyString());

        IexCloudException iexCloudException = assertThrows(IexCloudException.class, () -> {
            classUnderTest.getPrices("");
        });

    }
}