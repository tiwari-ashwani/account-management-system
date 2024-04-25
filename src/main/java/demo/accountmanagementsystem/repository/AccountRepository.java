package demo.accountmanagementsystem.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.openapi.gen.springboot.dto.CreateAccountResponse.AccountStatusEnum;

import demo.accountmanagementsystem.entity.Account;

public interface AccountRepository extends CrudRepository<Account, UUID>{
	
	
Optional<Account> findByIbanAndAccountStatus(String iban,AccountStatusEnum status);

}