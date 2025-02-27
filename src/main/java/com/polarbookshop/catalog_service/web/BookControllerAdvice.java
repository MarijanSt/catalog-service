package com.polarbookshop.catalog_service.web;

import com.polarbookshop.catalog_service.domain.exception.BookAlreadyExistsException;
import com.polarbookshop.catalog_service.domain.exception.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public String bookAlreadyExistsException(BookAlreadyExistsException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String bookNotFoundException(BookNotFoundException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> notValidParametersErrors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            notValidParametersErrors.put(fieldName, errorMessage);
        });
        Optional<String> finalError = notValidParametersErrors.values().stream().reduce((a,b) -> a + b);
        return finalError.orElse("No parameter error");
    }
}
