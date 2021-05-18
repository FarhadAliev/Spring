package com.matrix.freshmarket.repository;

import com.matrix.freshmarket.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
