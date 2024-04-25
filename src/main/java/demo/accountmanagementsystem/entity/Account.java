package demo.accountmanagementsystem.entity;


import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import com.openapi.gen.springboot.dto.CreateAccountResponse.AccountStatusEnum;
import com.openapi.gen.springboot.dto.CreateAccountResponse.AccountTypeEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Entity
@Accessors(fluent = true)
@Getter
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String iban;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum accountType;

    @Enumerated(EnumType.STRING)
    private AccountStatusEnum accountStatus;

    @Column(nullable = false)
    private BigDecimal currentBalance;
    
    @Column(nullable = false)
    private String emailId;
    
    @Column(nullable = false)
    private String contactNumber;
    
    @OneToMany(mappedBy = "account",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AccountTransaction> transactions;

}