package com.acme.mytrader.domain.execution;

import org.springframework.stereotype.Service;

public class ExecutionServiceImpl implements ExecutionService {

    @Override
    public void buy(String security, double price, int volume) {
        System.out.println("Congratulation! You have just bought " + volume + " units spending " + volume * price);
    }

    @Override
    public void sell(String security, double price, int volume) {
        System.out.println("Congratulation! You have just sold " + volume + " units earning " + volume * price);
    }
}
