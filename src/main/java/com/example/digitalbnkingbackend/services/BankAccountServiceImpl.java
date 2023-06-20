package com.example.digitalbnkingbackend.services;

import com.example.digitalbnkingbackend.dto.AccountBankDTO;
import com.example.digitalbnkingbackend.dto.CurrentAccountDTO;
import com.example.digitalbnkingbackend.dto.CustomerDTO;
import com.example.digitalbnkingbackend.dto.SavingAccountDTO;
import com.example.digitalbnkingbackend.entities.*;
import com.example.digitalbnkingbackend.enums.OperationType;
import com.example.digitalbnkingbackend.exeptions.BankAccountNotFoundException;
import com.example.digitalbnkingbackend.exeptions.BankAcountNotSuffisantAmount;
import com.example.digitalbnkingbackend.exeptions.CustomerNotFoundException;
import com.example.digitalbnkingbackend.mappers.MappersAccountImpl;
import com.example.digitalbnkingbackend.repositories.BankAccountRepository;
import com.example.digitalbnkingbackend.repositories.CustomerRepository;
import com.example.digitalbnkingbackend.repositories.OperationBankRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BankAccountServiceImpl implements BAnkAccountService{
    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private OperationBankRepository operationBankRepository;
    private MappersAccountImpl mappersAccount;

    //Customer
    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDto) {
        Customer customer1=mappersAccount.fromCustomerDto(customerDto);
        Customer customersaved=customerRepository.save(customer1);
        return mappersAccount.fromCustomer(customersaved);}
    @Override
    public List<CustomerDTO> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> mappersAccount.fromCustomer(customer))
                .collect(Collectors.toList());
        /*
        List<CustomerDTO> customerDTOS=new ArrayList<>();
        for (Customer customer:customers){
            CustomerDTO customerDTO=dtoMapper.fromCustomer(customer);
            customerDTOS.add(customerDTO);
        }
        *
         */
        return customerDTOS;
    }
    @Override
    public CustomerDTO getCustomer(Long customer_id) throws CustomerNotFoundException {
        Customer customer=customerRepository.findById(customer_id)
                .orElseThrow(()->new CustomerNotFoundException("Customer not found "));
        return mappersAccount.fromCustomer(customer);
    }
    @Override
     public void deleteCUstomer(Long cust_id){
        customerRepository.deleteById(cust_id);
    }
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO){
        Customer customer=mappersAccount.fromCustomerDto(customerDTO);
        Customer updateCustomer=customerRepository.save(customer);
        return mappersAccount.fromCustomer(updateCustomer);
    }
    //Banking account
    @Override
    public CurrentAccountDTO saveCurrentAccount(double initialBalance, double overDraft, Long customer_id) throws CustomerNotFoundException {
        Customer cus=customerRepository.findById(customer_id).orElse(null);
        if(cus==null) throw new CustomerNotFoundException("Customer not found");
        CourantAccount currentAccount = new CourantAccount();
        currentAccount.setDateCreation(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setOverDraft(overDraft);
        currentAccount.setCustomer(cus);
        CourantAccount savedCourantAaccount = bankAccountRepository.save(currentAccount);
        return mappersAccount.fromCurrentAccount(savedCourantAaccount);}
    @Override
    public SavingAccountDTO saveSavingAccount(double initialBalance, double interestRate,  Long Customer_id) throws CustomerNotFoundException {
      Customer customer=customerRepository.findById(Customer_id).orElse(null);
      if(customer==null) throw new CustomerNotFoundException("Customer not found");SavingAccount savingAccount = new SavingAccount();
      savingAccount.setDateCreation(new Date());
      savingAccount.setBalance(initialBalance);
      savingAccount.setInterestRate(interestRate);
      savingAccount.setCustomer(customer);
      SavingAccount savedSavingAccount = bankAccountRepository.save(savingAccount);
      return mappersAccount.fromSavingAccount(savedSavingAccount);    }
    @Override
    public AccountBankDTO getAccount(Long account_id) throws BankAccountNotFoundException {
        AccountBank accountBank=bankAccountRepository.findById(account_id).orElseThrow(()->new  BankAccountNotFoundException("Account not found "));
        if(accountBank instanceof SavingAccount){
            SavingAccount savingAccount=(SavingAccount) accountBank;
            return mappersAccount.fromSavingAccount(savingAccount);
        }else {
            CourantAccount courantAccount=(CourantAccount) accountBank;
            return mappersAccount.fromCurrentAccount(courantAccount);        }    }
    @Override
    public void debit(Long account_id, double amount, String description) throws BankAccountNotFoundException, BankAcountNotSuffisantAmount {
        AccountBank accountBank=bankAccountRepository.findById(account_id).orElseThrow(()->new  BankAccountNotFoundException("Account not found "));
            if (accountBank.getBalance()<amount) throw new BankAcountNotSuffisantAmount("Banlance not SUFFISANT ");
            AccountOperation accountOperation=new AccountOperation();
            accountOperation.setAccountBank(accountBank);
            accountOperation.setDateOperation(new Date());
            accountOperation.setAmount(amount);
            accountOperation.setOperationType(OperationType.DEBIT);
            operationBankRepository.save(accountOperation);
            double balance = accountBank.getBalance();
            balance=balance-amount;
            accountBank.setBalance(balance);
            bankAccountRepository.save(accountBank);    }
    @Override
    public void credit(Long account_id, double amount,String discription) throws BankAccountNotFoundException {
        AccountBank accountBank=bankAccountRepository.findById(account_id).orElseThrow(()->new  BankAccountNotFoundException("Account not found "));
        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setAccountBank(accountBank);
        accountOperation.setDateOperation(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setOperationType(OperationType.CREDIT);
        operationBankRepository.save(accountOperation);
        double balance = accountBank.getBalance();
        balance=balance+amount;
        accountBank.setBalance(balance);
        bankAccountRepository.save(accountBank);    }
    @Override
    public void transfer(Long accountIdSource,Long accountIdDestination, double amount) throws BankAccountNotFoundException, BankAcountNotSuffisantAmount {
        debit(accountIdSource,amount,"Transfer To "+accountIdDestination);
        credit(accountIdDestination,amount,"Transfer");   }
    @Override
    public List<AccountBankDTO> listBankacount() {
        List<AccountBank>  listaccounts= bankAccountRepository.findAll();
        List<AccountBankDTO> list=listaccounts.stream().map(listaccount->{

            if (listaccount instanceof SavingAccount){
                SavingAccount account=(SavingAccount) listaccount;
                return mappersAccount.fromSavingAccount(account);
            }else {
                CourantAccount account = (CourantAccount) listaccount;
                return mappersAccount.fromCurrentAccount(account);
            }

        }).collect(Collectors.toList());
        return list;


    }



}
