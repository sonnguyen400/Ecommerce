package com.nhs.individual.Constant;

public enum OrderStatus {
    PENDING(2,"PENDING"),
    PREPARING(3,"PREPARING"),
    DELIVERING(4,"DELIVERING"),
    DELIVERED(5,"DELIVERED"),
    COMPLETED(6,"COMPLETED"),
    RETURN(7,"RETURN"),
    EXCHANGE(8,"EXCHANGE");
    public final Integer id;
    public final String value;
    OrderStatus(Integer id, String value) {
        this.id=id;
        this.value=value;
    }
}
