package com.example.demo.config;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BaseException.class)
    protected BaseResponse customHandler(BaseException exception) {
        return new BaseResponse(exception.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected String beanValidationHandler(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("] is ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" // entered value: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]\n");
        }

        return builder.toString();
    }
}
