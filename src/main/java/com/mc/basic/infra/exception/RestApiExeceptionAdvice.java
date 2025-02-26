package com.mc.basic.infra.exception;

import com.mc.basic.infra.exception.exceptions.RestApiException;
import com.mc.basic.infra.response.ApiResponse;
import com.mc.basic.infra.response.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class RestApiExeceptionAdvice {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String,String>>> validExceptionHandler(
            MethodArgumentNotValidException ex
    ){
        Map<String,String> errors = new LinkedHashMap<>();
        ex.getAllErrors().forEach(e -> {
            errors.put(e.getCode(), e.getDefaultMessage());
        });

        return  ResponseEntity
                .badRequest()
                .body(ApiResponse.error(ResponseCode.BAD_REQUEST, errors));
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<String>> MethodNotSupportedExceptionHandler(
            HttpRequestMethodNotSupportedException ex
    ){

        return  ResponseEntity
                .status(ex.getStatusCode())
                .body(ApiResponse.error(ResponseCode.BAD_REQUEST, ex.getMessage()));
    }


    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<ApiResponse<Void>> restApiExceptionHandler(
            RestApiException ex
    ){
        return ResponseEntity
                .status(ex.code().status())
                .body(ApiResponse.error((ex.code())));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> globalHandler(RuntimeException ex){
        log.error(ex.getMessage());
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.error(ResponseCode.UNKNOWN,ex.getMessage()));

    }

}
