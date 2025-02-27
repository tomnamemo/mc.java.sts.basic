package com.mc.core.module.rest.response;

import java.time.LocalDateTime;

public record RestResponse(
        Integer id,
        String email,
        String name,
        LocalDateTime lastAccess
) {
}
