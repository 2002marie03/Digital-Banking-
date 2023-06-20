package com.example.digitalbnkingbackend.web;


import com.example.digitalbnkingbackend.dto.AccountBankDTO;
import com.example.digitalbnkingbackend.exeptions.BankAccountNotFoundException;
import com.example.digitalbnkingbackend.services.BAnkAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/")

public class BankAccountRestAPI {
    private BAnkAccountService bAnkAccountService;
    public BankAccountRestAPI(BAnkAccountService bAnkAccountService) {

        this.bAnkAccountService = bAnkAccountService;
    }
    @GetMapping("/accounts")
    public List<AccountBankDTO> listAccountBank(){

        return bAnkAccountService.listBankacount();
    }
    @GetMapping("/accounts/{account_id}")
    public AccountBankDTO getBankAccount(@PathVariable Long account_id) throws BankAccountNotFoundException {
        return bAnkAccountService.getAccount(account_id);

    }
}
