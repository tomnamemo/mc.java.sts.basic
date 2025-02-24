package com.mc.basic.module.form.request;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

// record
// 1. immutable
// 2. getter를 속성명으로 자동생성
public record FormRequest(
        @NotNull
        Integer id,
        @Email
        String email,
        @Length(min = 1, max = 3)
        String name,
        @NotNull(message = "날짜는 꼭 필요해요!")
        LocalDateTime createdAt
) {

}