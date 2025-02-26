package com.mc.basic.infra.exception.exceptions;

import com.mc.basic.infra.response.ResponseCode;

public class RestApiException extends  CommonException{
    public RestApiException(ResponseCode code) {
        super(code);
    }

    public RestApiException(ResponseCode code, Exception e) {
        super(code, e);
    }
}
