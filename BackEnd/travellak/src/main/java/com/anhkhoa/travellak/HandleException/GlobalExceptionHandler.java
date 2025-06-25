package com.anhkhoa.travellak.HandleException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<Map<String, String>> handleValidationExceptions(Exception ex) {
        BindingResult bindingResult = ex instanceof MethodArgumentNotValidException
                ? ((MethodArgumentNotValidException) ex).getBindingResult()
                : ((BindException) ex).getBindingResult();

        Map<String, String> errors = new HashMap<>();
        bindingResult.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
