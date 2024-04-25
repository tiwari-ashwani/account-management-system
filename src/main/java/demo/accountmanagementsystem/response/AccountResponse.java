package demo.accountmanagementsystem.response;

import java.util.List;

import com.openapi.gen.springboot.dto.AccountDetails;

public record AccountResponse(List<AccountDetails> acccountDetails) {
	
}