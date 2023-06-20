package com.example.digitalbnkingbackend.services;

import com.example.digitalbnkingbackend.entities.AccountBank;
import com.example.digitalbnkingbackend.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){

    }
}
