package com.acme.mytrader;

import com.acme.mytrader.domain.price.listeners.IbmListener;
import com.acme.mytrader.domain.price.source.iexcloud.IexCloudSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
public class Application {

    @Autowired
    private IexCloudSource iexCloudSource;

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

	@PostConstruct
	public void init() {
		iexCloudSource.addPriceListener(new IbmListener(150));
	}
}
