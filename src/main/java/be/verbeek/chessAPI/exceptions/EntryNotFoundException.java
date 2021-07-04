package be.verbeek.chessAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntryNotFoundException  extends RuntimeException{

    public EntryNotFoundException(String object, String property, String value){
        super(String.format("%s not found with %s : %s", object, property, value));
    }
}
