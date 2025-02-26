package com.mc.basic.module.error;


import com.mc.basic.infra.exception.exceptions.RestApiException;
import com.mc.basic.infra.exception.exceptions.ViewException;
import com.mc.basic.infra.response.ResponseCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class ErrorController {

    @GetMapping("view")
    public void view() {
        throw  new ViewException((ResponseCode.BAD_REQUEST));
    }

    @GetMapping("rest")
    public void rest() {
        throw  new RestApiException((ResponseCode.UNAUTHORIZED));
    }
}
