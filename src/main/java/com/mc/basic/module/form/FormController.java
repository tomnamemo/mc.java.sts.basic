package com.mc.basic.module.form;

import com.mc.basic.module.form.request.FormRequest;
import com.mc.basic.module.form.response.FormResponse;
import com.mc.basic.module.form.validator.FormValidator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
import java.util.List;

@Controller
@RequestMapping("form")
@SuppressWarnings("Duplicates")
public class FormController {
    
    // request content-type : application/x-www-form-url-encoded 일때 컨트롤러
    // method return type : String, mav, void

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
            return "spring/validate";
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

    @GetMapping("cookie/regist")
    public String registCookie(HttpServletResponse response){

        // cookie : 브라우저가 서버에 자동으로 전달하는 데이터 쪼가리
        // 생성: 서버 : 응답 header 에 set-cookie를 지정해서 쿠키를 브라우저에 저장
        //      클라이언트 : cookie를 생성해 브라우저에 저장
        
        //생성된 쿠키는 cookie의 domain에 포함될 경우 자동으로 서버로 전송
        //domain : 쿠키가 전송될 도메인 http + domain +portNumber
        //path : / , 사이트 내의 모든 요청에 대해 쿠키가 전송
        //          /a/b , /a/b로 uri가 시작하는 모든 요청에 대해 쿠키가 전송

        //생성된 쿠키는 수명을 가진다.
        //max-age, expires : 초 단위로 저장, max-age가 우선적용

        //단점 : 쿠키는 보안에 취약하다.
        //1. http 통신 도중 유출될 가능성이 있다.
        //      secure : https 통신일 경우에만 브라우저가 쿠키를 서버에 전송
        //2. xss 공격에 취약, 자바스크립트로 브라우저에 저장된 cookie에 접근 할 수 있기 때문에
        //      http: 자바스크립트가 쿠키에 접근하는것을 차단


        Cookie cookie = new Cookie("server_cookie","this_is_server_cookie:");
        response.addCookie(cookie);
        return "redirect:/form/cookie/confirm";
    }


    @GetMapping("cookie/confirm")
    public String confirmCookie(
            @CookieValue("server_cookie")
            String serverCookie,
            @CookieValue("JSESSIONID")
            String sessionId,
            Model model
    ){
        log.info("server cookie : {}", serverCookie);
        log.info("session id : {}", sessionId);

        model.addAttribute("cookies", List.of(serverCookie,sessionId));
        return "spring/cookie";
    }

    private FormResponse generateFormResponse(FormRequest request) {
        return new FormResponse(request.id(), request.email(), request.name(), LocalDateTime.now());
    }
}