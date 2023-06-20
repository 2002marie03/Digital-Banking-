package com.example.digitalbnkingbackend.dto;



import com.example.digitalbnkingbackend.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class SavingAccountDTO extends AccountBankDTO {
    private Long id ;
    private double solde ;
    private Date dateCreation;
    private double balance ;
    private AccountStatus accountStatus;
    private String currency ;
    private CustomerDTO customerDTO;
    private  double intrestRate;
}
