package com.example.digitalbnkingbackend.services;

import com.example.digitalbnkingbackend.dto.AccountBankDTO;
import com.example.digitalbnkingbackend.dto.CurrentAccountDTO;
import com.example.digitalbnkingbackend.dto.CustomerDTO;
import com.example.digitalbnkingbackend.dto.SavingAccountDTO;
import com.example.digitalbnkingbackend.entities.AccountBank;
import com.example.digitalbnkingbackend.entities.CourantAccount;
import com.example.digitalbnkingbackend.entities.Customer;
import com.example.digitalbnkingbackend.entities.SavingAccount;
import com.example.digitalbnkingbackend.exeptions.BankAccountNotFoundException;
import com.example.digitalbnkingbackend.exeptions.BankAcountNotSuffisantAmount;
import com.example.digitalbnkingbackend.exeptions.CustomerNotFoundException;

import java.util.List;

public interface BAnkAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDto);
    void deleteCUstomer(Long cust_id);
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    CurrentAccountDTO saveCurrentAccount(double initialBalance ,  double overDraft, Long Customer_id) throws CustomerNotFoundException;
    SavingAccountDTO saveSavingAccount(double initialBalance , double interestRate,  Long Customer_id) throws CustomerNotFoundException;
     AccountBankDTO getAccount(Long account_id) throws BankAccountNotFoundException;
    void debit(Long account_id,double amount , String description) throws BankAccountNotFoundException, BankAcountNotSuffisantAmount;
    void credit(Long account_id,double amount , String description) throws BankAccountNotFoundException;
    void transfer(Long accountIdSource,Long accountIdDestination,double amount) throws BankAccountNotFoundException, BankAcountNotSuffisantAmount;
    List<AccountBankDTO> listBankacount();
    List<CustomerDTO> listCustomers();
    CustomerDTO getCustomer(Long customerDto_id) throws CustomerNotFoundException;

}
