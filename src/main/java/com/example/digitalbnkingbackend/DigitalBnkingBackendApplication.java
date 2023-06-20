package com.example.digitalbnkingbackend;

import com.example.digitalbnkingbackend.dto.AccountBankDTO;
import com.example.digitalbnkingbackend.dto.CurrentAccountDTO;
import com.example.digitalbnkingbackend.dto.CustomerDTO;
import com.example.digitalbnkingbackend.dto.SavingAccountDTO;
import com.example.digitalbnkingbackend.entities.CourantAccount;
import com.example.digitalbnkingbackend.entities.Customer;
import com.example.digitalbnkingbackend.entities.SavingAccount;
import com.example.digitalbnkingbackend.enums.AccountStatus;
import com.example.digitalbnkingbackend.exeptions.CustomerNotFoundException;
import com.example.digitalbnkingbackend.repositories.BankAccountRepository;
import com.example.digitalbnkingbackend.repositories.CustomerRepository;
import com.example.digitalbnkingbackend.repositories.OperationBankRepository;
import com.example.digitalbnkingbackend.services.BAnkAccountService;
import com.example.digitalbnkingbackend.services.BankAccountServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBnkingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBnkingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BAnkAccountService bankAccountService){
        return args -> {
            Stream.of("Hassan","Imane","Mohamed").forEach(name->{
                CustomerDTO customer=new CustomerDTO();
                customer.setNom(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer->{
                try {
                    bankAccountService.saveCurrentAccount(Math.random()*90000,9000,customer.getId());
                    bankAccountService.saveSavingAccount(Math.random()*120000,5.5,customer.getId());


                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });
          List<AccountBankDTO> bankAccounts = bankAccountService.listBankacount();
            for (AccountBankDTO bankAccount:bankAccounts){
                for (int i = 0; i <10 ; i++) {
                    Long accountId;
                    if(bankAccount instanceof SavingAccountDTO){
                        accountId=((SavingAccountDTO) bankAccount).getId();
                    } else{
                        accountId=((CurrentAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
                    bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
                }
            }        };
    }
    //@Bean
   /* CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Hassan","Yassine","Aicha").forEach(name->{
                Customer customer=new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust->{
                CurrentAccount currentAccount=new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount=new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);

            });
            bankAccountRepository.findAll().forEach(acc->{
                for (int i = 0; i <10 ; i++) {
                    AccountOperation accountOperation=new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()*12000);
                    accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }

            });
        };*/
}
