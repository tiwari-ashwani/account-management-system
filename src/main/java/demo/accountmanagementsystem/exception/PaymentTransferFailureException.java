package demo.accountmanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PaymentTransferFailureException extends RuntimeException {
    public PaymentTransferFailureException(String message) {
        super(message);
    }
}

