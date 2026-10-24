package com.smartbank.transaction.repository;

import com.smartbank.transaction.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    
    // UserId tho anni transactions teeyadaniki - BOT ki kavali
    List<Transaction> findByUserId(String userId);
    
    // UserId + Category tho filter cheyadaniki - Ex: food entha karchu?
    List<Transaction> findByUserIdAndCategory(String userId, String category);
    
    List<Transaction> findByUserIdAndMerchant(String userId, String merchant);
}