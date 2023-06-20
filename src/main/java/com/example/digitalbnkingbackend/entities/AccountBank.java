package com.example.digitalbnkingbackend.entities;

import com.example.digitalbnkingbackend.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 4)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBank {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private double solde ;
    private Date dateCreation;
    private double balance ;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    private String currency ;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "accountBank",fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperations;
}
