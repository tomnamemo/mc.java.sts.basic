package com.mc.basic.module.rest.response;

import java.time.LocalDateTime;

public record RestResponse(
        Integer id,
        String email,
        String name,
        LocalDateTime lastAccess
) {
}
