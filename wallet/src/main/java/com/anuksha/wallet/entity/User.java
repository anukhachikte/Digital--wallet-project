package com.anuksha.wallet.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private Double balance;

    // Constructors
    public User() {}

    public User(String username, String password, Double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
}