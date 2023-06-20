package com.example.digitalbnkingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@DiscriminatorValue("CA")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CourantAccount extends AccountBank{
    private Double overDraft;
}
