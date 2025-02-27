package com.mc.core.module.thymeleaf.controller;

import com.mc.core.module.thymeleaf.response.AttrResponse;
import com.mc.core.module.thymeleaf.response.ExampleResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {

    @GetMapping("example")
    public void example(Model model) {

        ExampleResponse example = new ExampleResponse("하명도", 35, "스프링부트", 100, false);

        Map<String, Integer> devScore = new LinkedHashMap<>();
        devScore.put("java", 100);
        devScore.put("db", 50);
        devScore.put("html", 70);
        devScore.put("js", 83);
        devScore.put("spring", 95);

        AttrResponse attr = new AttrResponse("red", "lightcoral");

        // cross site script attack
        // 사용자의 브라우저에서 스크립트가 실행되게끔 하여 공격
        String xssAttack = "<script>alert('xss Attack에 당하셨습니다.')</script>";

        model.addAttribute("example", example);
        model.addAttribute("devScore", devScore);
        model.addAttribute("attr", attr);
        model.addAttribute("xss", xssAttack);
    }
}