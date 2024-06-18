package com.nhs.individual.constant;

public enum OrderStatus {
    PENDING(2,"PENDING"),
    PREPARING(3,"PREPARING"),
    DELIVERING(4,"DELIVERING"),
    DELIVERED(5,"DELIVERED"),
    COMPLETED(6,"COMPLETED"),
    CANCEL(7,"CANCEL"),
    RETURN(8,"RETURN"),
    EXCHANGE(9,"EXCHANGE"),
    ;
    public final Integer id;
    public final String value;
    OrderStatus(Integer id, String value) {
        this.id=id;
        this.value=value;
    }
}
