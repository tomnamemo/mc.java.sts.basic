package com.mc.core.module.rest.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record RestRequest(
        Integer id,
        @Email
        String email,
        @Length(min = 1, max = 4)
        String name,
        @NotNull
        LocalDateTime createdAt
) {
}