package com.acme.mytrader.domain.price.source.iexcloud;

public class IexCloudException extends RuntimeException {
    public IexCloudException() {
        super();
    }

    public IexCloudException(String message) {
        super(message);
    }
}
