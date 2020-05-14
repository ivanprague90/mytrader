package com.acme.mytrader.domain.price.source.iexcloud;

public class IexCloudResponse {

    private Double price;

    private String security;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }
}
