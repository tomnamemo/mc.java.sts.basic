package com.mc.core.infra.exception.exceptions;

import com.mc.core.infra.response.ResponseCode;

public class ViewException extends CommonException{

    public ViewException(ResponseCode code) {
        super(code);
    }

    public ViewException(ResponseCode code, Exception e) {
        super(code, e);
    }
}
