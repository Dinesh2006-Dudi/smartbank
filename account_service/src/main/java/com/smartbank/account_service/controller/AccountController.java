package com.smartbank.account.controller;
import com.smartbank.account.model.Account;
import com.smartbank.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
public class AccountController {
    @Autowired private AccountRepository accountRepository;

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        account.setAccountNumber(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        return accountRepository.save(account);
    }

    @GetMapping("/user/{userId}")
    public List<Account> getByUserId(@PathVariable String userId) {
        return accountRepository.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable String id) {
        return accountRepository.findById(id).orElse(null);
    }
}