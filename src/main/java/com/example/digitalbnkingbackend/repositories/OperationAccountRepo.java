package com.example.digitalbnkingbackend.repositories;

import com.example.digitalbnkingbackend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationAccountRepo extends
        JpaRepository<AccountOperation,Long> {

    public List<AccountOperation> findAccountOperationById(Long account_id);
}
