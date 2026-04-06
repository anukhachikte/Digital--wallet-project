package com.anuksha.wallet.dto;

public class AddMoneyRequest {

    private Long userId;
    private Double amount;

    public Long getUserId() {
        return userId;
    }

    public Double getAmount() {
        return amount;
    }
}