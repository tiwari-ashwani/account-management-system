package demo.accountmanagementsystem.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountManagementExceptionHandler {

  @ExceptionHandler(AccountNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String accountNotFoundHandler(AccountNotFoundException ex) {
    return ex.getMessage();
  }
  
  @ExceptionHandler(InsufficientBalanceException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String insufficientBalanceHandler(InsufficientBalanceException ex) {
    return ex.getMessage();
  }
  
  @ExceptionHandler(PaymentTransferFailureException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  String fundsTransferFailureHandler(PaymentTransferFailureException ex) {
    return ex.getMessage();
  }
  
  
}