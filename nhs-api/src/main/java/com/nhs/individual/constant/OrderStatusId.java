package com.nhs.individual.constant;

public enum OrderStatusId {
    CANCEL,PURCHASING,PENDING,PREPARING,DELIVERING,DELIVERED,RETURN,EXCHANGE,REFUNDED;
    private int value;
    public int value(){
        return value;
    }

}
