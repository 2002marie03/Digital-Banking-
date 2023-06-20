package com.example.digitalbnkingbackend.mappers;

import com.example.digitalbnkingbackend.dto.AccountBankDTO;
import com.example.digitalbnkingbackend.dto.CurrentAccountDTO;
import com.example.digitalbnkingbackend.dto.CustomerDTO;
import com.example.digitalbnkingbackend.dto.SavingAccountDTO;
import com.example.digitalbnkingbackend.entities.AccountBank;
import com.example.digitalbnkingbackend.entities.CourantAccount;
import com.example.digitalbnkingbackend.entities.Customer;
import com.example.digitalbnkingbackend.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MappersAccountImpl {
    public CustomerDTO fromCustomer(Customer customer){
        CustomerDTO customerDTO=new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        /*customerDTO.setId(UUID.randomUUID().toString());
        customerDTO.setNom(customer.getNom());
        customerDTO.setEmail(customer.getEmail());*/
        return customerDTO;
    }
    public Customer fromCustomerDto(CustomerDTO customerDTO){
         Customer customer=new Customer();
         BeanUtils.copyProperties(customerDTO,customer);
         return customer;
    }
    public SavingAccountDTO fromSavingAccount(SavingAccount savingAccount){
        SavingAccountDTO  savingAccountDTO=new SavingAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingAccountDTO);
        savingAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        return savingAccountDTO;
    }
    public SavingAccount fromSavingAccountDTO(SavingAccountDTO savingAccountDTO){
        SavingAccount  savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingAccountDTO,savingAccount);
        savingAccount.setCustomer(fromCustomerDto(savingAccountDTO.getCustomerDTO()));
        return savingAccount;
    }
    public CurrentAccountDTO fromCurrentAccount(CourantAccount currentAccount){
        CurrentAccountDTO  currentAccountDTO=new CurrentAccountDTO();
        BeanUtils.copyProperties(currentAccount,currentAccountDTO);
        currentAccountDTO.setCustomer(fromCustomer(currentAccount.getCustomer()));
        return currentAccountDTO;
    }

    public CourantAccount fromCurrentAccountDTO(CurrentAccountDTO currentAccountDTO){
        CourantAccount  currentAccount=new CourantAccount();
        BeanUtils.copyProperties(currentAccountDTO,currentAccount);
        currentAccount.setCustomer(fromCustomerDto(currentAccountDTO.getCustomer()));
        return currentAccount;
    }








}
