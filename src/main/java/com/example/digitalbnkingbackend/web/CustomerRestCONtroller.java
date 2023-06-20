package com.example.digitalbnkingbackend.web;

import com.example.digitalbnkingbackend.dto.CustomerDTO;
import com.example.digitalbnkingbackend.entities.Customer;
import com.example.digitalbnkingbackend.exeptions.CustomerNotFoundException;
import com.example.digitalbnkingbackend.services.BankAccountServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@Slf4j
@RequestMapping("/")
public class CustomerRestCONtroller {
    private BankAccountServiceImpl bankAccountService;
    @GetMapping("/customers")
    public List<CustomerDTO> listcustomers(){
        return bankAccountService.listCustomers();
    }
    @GetMapping(path = "/customers/{cust_id}")
    public  CustomerDTO getCustomerDto(@PathVariable  Long cust_id) throws CustomerNotFoundException {
        return bankAccountService.getCustomer(cust_id);    }
    @PostMapping("/customers")
    public CustomerDTO savecustomer(@RequestBody CustomerDTO customer1){
        return bankAccountService.saveCustomer(customer1 );    }
    @DeleteMapping("/customers/{custId}")
    public void deleteCustomer(@PathVariable Long custId){
        bankAccountService.deleteCUstomer(custId);    }
    @PutMapping("/customers/{custId}")
    public CustomerDTO updateCustomer(@PathVariable Long custId ,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(custId);
        return bankAccountService.updateCustomer(customerDTO);    }
}
