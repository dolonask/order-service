package kg.easy.orderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class IncorrectStatus extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public IncorrectStatus(String message){
        super(message);
    }
}