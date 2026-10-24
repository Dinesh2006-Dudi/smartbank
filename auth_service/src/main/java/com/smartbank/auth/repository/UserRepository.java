package com.smartbank.auth.repository;
import com.smartbank.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}