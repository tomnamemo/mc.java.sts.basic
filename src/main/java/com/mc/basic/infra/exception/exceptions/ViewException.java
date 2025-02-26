package com.mc.basic.infra.exception.exceptions;

import com.mc.basic.infra.response.ResponseCode;

public class ViewException extends CommonException{

    public ViewException(ResponseCode code) {
        super(code);
    }

    public ViewException(ResponseCode code, Exception e) {
        super(code, e);
    }
}
