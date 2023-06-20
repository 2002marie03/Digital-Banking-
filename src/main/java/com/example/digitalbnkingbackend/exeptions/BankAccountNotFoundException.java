package com.example.digitalbnkingbackend.exeptions;

public class BankAccountNotFoundException extends Exception{
 public BankAccountNotFoundException(String message ){
     super(message);
 }
}
