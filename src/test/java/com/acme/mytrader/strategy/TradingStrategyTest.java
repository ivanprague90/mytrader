package com.acme.mytrader.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TradingStrategyTest {

    @SpyBean
    TradingStrategy tradingStrategy;

    @Test
    @DisplayName("getInvocationCount return a number greater than 0 after 3000ms")
    public void getInvocationCount_givenSleepBy3000ms_whenGetInvocationCount_thenIsGreaterThanZero()
            throws InterruptedException {
        Thread.sleep(3000L);

        assertThat(tradingStrategy.getInvocationCount()).isGreaterThan(0);

    }
}