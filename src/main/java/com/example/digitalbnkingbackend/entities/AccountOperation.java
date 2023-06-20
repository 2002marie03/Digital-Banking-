package com.example.digitalbnkingbackend.entities;

import com.example.digitalbnkingbackend.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateOperation;
    private Double amount ;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @ManyToOne
    private AccountBank accountBank;
    private  String description;
}
