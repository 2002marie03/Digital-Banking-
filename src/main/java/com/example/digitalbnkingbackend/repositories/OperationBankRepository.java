package com.example.digitalbnkingbackend.repositories;

import com.example.digitalbnkingbackend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationBankRepository extends
        JpaRepository<AccountOperation,Long> {

}
