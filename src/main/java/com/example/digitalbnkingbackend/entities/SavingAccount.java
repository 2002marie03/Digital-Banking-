package com.example.digitalbnkingbackend.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@DiscriminatorValue("SA")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class SavingAccount extends AccountBank{
    private Double interestRate;
}
