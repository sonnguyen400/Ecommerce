package com.nhs.individual.Constant;

public enum ProductStatus {
    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE");
    public final String value;
    ProductStatus(String value){
        this.value=value;
    }
}
