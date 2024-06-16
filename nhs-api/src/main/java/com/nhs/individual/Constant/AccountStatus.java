package com.nhs.individual.Constant;

public enum AccountStatus {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    LOCKED("LOCKED");
    public final String value;
    AccountStatus(String value){
        this.value=value;
    }
}
