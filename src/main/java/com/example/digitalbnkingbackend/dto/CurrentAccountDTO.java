package com.example.digitalbnkingbackend.dto;

import com.example.digitalbnkingbackend.entities.AccountOperation;
import com.example.digitalbnkingbackend.entities.Customer;
import com.example.digitalbnkingbackend.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
public class CurrentAccountDTO extends  AccountBankDTO{
    private Long id;
    private double solde;
    private Date dateCreation;
    private double balance;
    private AccountStatus accountStatus;
    private String currency;
    private CustomerDTO customer;
    private  double overDraft;
}
