package com.mini.erp.product.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionErrorHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ErrorMessage>> globalException(MethodArgumentNotValidException ex, HttpServletRequest request){

        //The MethodArgumentNotValidException is thrown by Spring Boot when validation on an argument annotated with @Valid

        List<ErrorMessage> errorMessages = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(err -> new ErrorMessage(err.getDefaultMessage(), err.getCode(),
                        err.getObjectName(), request.getRequestURI()))
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errorMessages);
    }


    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> productNotFoundException(ProductNotFoundException ex, HttpServletRequest request){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request){
        String errorFormat = String.format(String.format("Parameter '%s' should be of type '%s'. Received value: '%s'.",
                ex.getName(),
                ex.getRequiredType().getSimpleName(),
                ex.getValue()));

        ErrorMessage errorMessage = new ErrorMessage(errorFormat, HttpStatus.BAD_REQUEST, request.getRequestURI());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
