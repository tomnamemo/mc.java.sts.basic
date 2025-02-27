package com.mc.core.infra.exception;

import com.mc.core.infra.exception.exceptions.ViewException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(1)
public class ViewExceptionAdvice {

    private final Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @ExceptionHandler(ViewException.class)
    public String viewExceptionHandler(ViewException e, Model model) {
        model.addAttribute("message", e.code().message());
        return "error/redirect";
    }
}
