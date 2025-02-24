package com.mc.basic.module.form;

import com.mc.basic.module.form.request.FormRequest;
import com.mc.basic.module.form.response.FormResponse;
import com.mc.basic.module.form.validator.FormValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("form")
public class FormController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @InitBinder("formRequest")
    private void formInitBinder(WebDataBinder binder) {
        binder.addValidators(new FormValidator());
    }

    @GetMapping
    public String getForm(
            FormRequest request, // @ModelAttribute 암묵적 바인드
            Model model
    ){
        log.info("form : {}", request);

        FormResponse response = generateFormResponse(request);
        model.addAttribute("response", response);

        log.info("model : {}", model);
        return "spring/example";
    }

    @PostMapping
    public String postForm(
            @Valid
            FormRequest request, // @ModelAttribute 암묵적 바인드
            BindingResult bindingResult,
            Model model
    ){
        log.info("model : {}", model);
        log.info("bindingResult : {}", bindingResult);

        if(bindingResult.hasErrors()) {
            return "spring/example";
        }

        FormResponse response = generateFormResponse(request);
        model.addAttribute("response", response);
        return "spring/example";
    }
    // pathVariable : 동적경로
    @PostMapping("id/{id}")
    public String postForm(
            @PathVariable
            int id,
            String email,
            String name,
            LocalDateTime createdAt,
            Model model
    ){
        model.addAttribute("response"
                , new FormResponse(id, email, name, createdAt));
        return "spring/example";
    }

    @PostMapping("session/regist")
    public String registSession(FormRequest request, HttpSession session){
        session.setAttribute("account", request);
        return "redirect:/form/session/confirm";
    }

    @GetMapping("session/confirm")
    public String confirmSession(
            @SessionAttribute
            FormRequest account
    ){
        log.info("session : {}", account);
        return "spring/session";
    }


    private FormResponse generateFormResponse(FormRequest request) {
        return new FormResponse(request.id(), request.email(), request.name(), LocalDateTime.now());
    }
}