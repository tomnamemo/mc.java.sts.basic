package com.mc.core.module.rest.validator;

import com.mc.core.module.rest.request.RestRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RestRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RestRequest request = (RestRequest) target;
        if(request.id() == 0){
            errors.rejectValue("id", "ID", "아이디는 0일 수 없습니다.");
        }
    }
}