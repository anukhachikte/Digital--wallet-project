package com.anuksha.wallet.dto;

public class UserResponse {

    private Long id;
    private String username;
    private Double balance;

    public UserResponse(Long id, String username, Double balance) {
        this.id = id;
        this.username = username;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Double getBalance() {
        return balance;
    }
}