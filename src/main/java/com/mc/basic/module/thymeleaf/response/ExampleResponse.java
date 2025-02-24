package com.mc.basic.module.thymeleaf.response;

public record ExampleResponse(
        String name,
        Integer age,
        String subject,
        Integer score,
        Boolean isStudent
) {
}