package com.smartbank.transaction.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transactions")
public class Transaction {
    
    @Id
    private String id;
    
    private String userId; // evaridi transaction
    
    private Double amount;
    
    private String category; // food, travel, shopping, bills
    
    private String merchant; // Zomato, Uber, Amazon
    
    private String description;
    
    private Date date = new Date();
}