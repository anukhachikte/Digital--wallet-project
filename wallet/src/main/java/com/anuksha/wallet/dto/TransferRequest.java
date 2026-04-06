package com.anuksha.wallet.dto;

public class TransferRequest {

    private Long senderId;
    private Long receiverId;
    private Double amount;

    public Long getSenderId() {
        return senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public Double getAmount() {
        return amount;
    }
}