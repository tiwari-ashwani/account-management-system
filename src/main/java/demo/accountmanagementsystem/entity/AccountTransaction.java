package demo.accountmanagementsystem.entity;


import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.AllArgsConstructor;


@Entity
@Accessors(fluent = true)
@Getter
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; 
    
    @Column
    private UUID transactionId; 

    @Column(nullable = false)
    private BigDecimal amount;
    
    @Column
    private String transactionStatus;
    
   
    @ManyToOne(optional=false)
    @JoinColumn(name = "account_id",nullable=false)
    private Account account;


}