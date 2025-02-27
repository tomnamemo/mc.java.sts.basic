package com.mc.core.infra.exception.exceptions;

import com.mc.core.infra.response.ResponseCode;

public class RestApiException extends  CommonException{
    public RestApiException(ResponseCode code) {
        super(code);
    }

    public RestApiException(ResponseCode code, Exception e) {
        super(code, e);
    }
}
