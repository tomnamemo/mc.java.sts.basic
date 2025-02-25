package com.mc.basic.module.rest.request;

import java.time.LocalDateTime;

public record RestRequest(
        Integer id,
        String email,
        String name,
        LocalDateTime lastAccess
) {
}
