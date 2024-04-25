package demo.accountmanagementsystem.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import demo.accountmanagementsystem.entity.AccountTransaction;

public interface TransactionRepository extends CrudRepository<AccountTransaction, UUID>{
	


}