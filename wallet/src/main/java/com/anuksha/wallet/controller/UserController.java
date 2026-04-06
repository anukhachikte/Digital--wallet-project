package com.anuksha.wallet.controller;
import com.anuksha.wallet.dto.TransferRequest;
import com.anuksha.wallet.entity.User;
import com.anuksha.wallet.service.UserService;
import com.anuksha.wallet.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.anuksha.wallet.dto.AddMoneyRequest;
import java.util.List;
import com.anuksha.wallet.entity.Transaction;
@CrossOrigin(origins = "http://localhost:5174")
@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private UserService userService;

    // REGISTER
    @PostMapping("/register")
    public UserResponse register(@RequestBody User user) {
        User savedUser = userService.registerUser(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getBalance()
        );
    }

    // LOGIN
    @PostMapping("/login")
    public UserResponse login(@RequestBody User user) {

        User loggedInUser = userService.login(
                user.getUsername(),
                user.getPassword()
        );

        if (loggedInUser != null) {
            return new UserResponse(
                    loggedInUser.getId(),
                    loggedInUser.getUsername(),
                    loggedInUser.getBalance()
            );
        }

        return null;
    }

    @PostMapping("/add")
    public UserResponse addMoney(@RequestBody AddMoneyRequest request) {

        User updatedUser = userService.addMoney(
                request.getUserId(),
                request.getAmount()
        );

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getBalance()
        );
    }
    @PostMapping("/transfer")
    public String transferMoney(@RequestBody TransferRequest request) {

        userService.transferMoney(
                request.getSenderId(),
                request.getReceiverId(),
                request.getAmount()
        );

        return "Transfer Successful";
    }
    @GetMapping("/transactions/{userId}")
    public List<Transaction> getTransactions(@PathVariable Long userId) {

        return userService.getUserTransactions(userId);
    }
}