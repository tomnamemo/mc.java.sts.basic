package com.mc.core.infra.exception.exceptions;

import com.mc.core.infra.response.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// cathed : try - catch를 강제하는 예외
// uncathed : try - catch를 강제하지 않음
public class CommonException extends RuntimeException {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());


    private ResponseCode code;

    public CommonException(ResponseCode code) {
        this.code = code;
    }
    public CommonException(ResponseCode code, Exception e) {
        this.code = code;
        log.error(e.getMessage(),e);
    }

    public ResponseCode code() {
        return code;
    }
}
