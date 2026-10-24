package com.smartbank.account.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data; import lombok.NoArgsConstructor; import lombok.AllArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor @Document(collection = "accounts")
public class Account {
    @Id private String id;
    private String userId;
    private String accountNumber;
    private Double balance = 0.0;
    private String accountType = "SAVINGS";
}