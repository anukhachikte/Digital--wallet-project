package com.anuksha.wallet.service;

import com.anuksha.wallet.entity.Transaction;
import com.anuksha.wallet.entity.User;
import com.anuksha.wallet.repository.TransactionRepository;
import com.anuksha.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ---------------- REGISTER ----------------
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getBalance() == null) {
            user.setBalance(0.0);
        }

        return userRepository.save(user);
    }

    // ---------------- LOGIN ----------------
    public User login(String username, String password) {

        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }

        return null;
    }

    // ---------------- ADD MONEY ----------------
    public User addMoney(Long userId, Double amount) {

        if (amount <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setBalance(user.getBalance() + amount);

        return userRepository.save(user);
    }

    // ---------------- TRANSFER MONEY ----------------
    @Transactional
    public void transferMoney(Long senderId, Long receiverId, Double amount) {

        if (amount <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        if (senderId.equals(receiverId)) {
            throw new RuntimeException("Sender and Receiver cannot be same");
        }

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // Deduct from sender
        sender.setBalance(sender.getBalance() - amount);

        // Add to receiver
        receiver.setBalance(receiver.getBalance() + amount);

        userRepository.save(sender);
        userRepository.save(receiver);

        // Save transaction record
        Transaction transaction = new Transaction(
                sender.getId(),
                receiver.getId(),
                amount,
                LocalDateTime.now()
        );

        transactionRepository.save(transaction);
    }
    public List<Transaction> getUserTransactions(Long userId) {

        return transactionRepository
                .findBySenderIdOrReceiverId(userId, userId);
    }
}