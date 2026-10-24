package com.smartbank.transaction.controller;

import com.smartbank.transaction.model.Transaction;
import com.smartbank.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*") // Gateway nundi request ravali ante idi kavali
public class TransactionController {

    @Autowired
    private TransactionRepository repo;

    // 1. Kotha transaction add chey
    @PostMapping("/add")
    public Transaction addTransaction(@RequestBody Transaction tx) {
        return repo.save(tx);
    }

    // 2. Oka user vi anni transactions - Bot idhe vadutadi
    @GetMapping("/{userId}")
    public List<Transaction> getByUserId(@PathVariable String userId) {
        return repo.findByUserId(userId);
    }

    // 3. Category wise - Ex: /api/transactions/1/category/food
    @GetMapping("/{userId}/category/{category}")
    public List<Transaction> getByCategory(@PathVariable String userId, @PathVariable String category) {
        return repo.findByUserIdAndCategory(userId, category);
    }

    // 4. Test kosam - anni transactions
    @GetMapping("/all")
    public List<Transaction> getAll() {
        return repo.findAll();
    }
}