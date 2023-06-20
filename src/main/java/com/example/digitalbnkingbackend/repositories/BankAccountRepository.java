package com.example.digitalbnkingbackend.repositories;

import com.example.digitalbnkingbackend.entities.AccountBank;
import com.example.digitalbnkingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends
        JpaRepository<AccountBank,Long> {
}
