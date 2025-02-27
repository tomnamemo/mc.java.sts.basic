package com.mc.core.module.form.validator;


import com.mc.core.module.form.request.FormRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return FormRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FormRequest formRequest = (FormRequest) target;
        if(formRequest.id() == 0){
            errors.rejectValue("id", "field.invalide", "아이디는 0일 수 없습니다.");
        }
    }
}