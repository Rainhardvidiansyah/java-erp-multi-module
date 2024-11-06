package com.mini.erp.product.exception;

import org.springframework.core.MethodParameter;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public class ArgumentIsNotValidException extends MethodArgumentTypeMismatchException {

    public ArgumentIsNotValidException(Object value, Class<?> requiredType, String name, MethodParameter param, Throwable cause) {
        super(value, requiredType, name, param, cause);
    }
}
