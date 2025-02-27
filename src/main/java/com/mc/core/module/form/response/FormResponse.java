package com.mc.core.module.form.response;

import java.time.LocalDateTime;

public record FormResponse (
        Integer id,
        String email,
        String name,
        LocalDateTime lastAccess
){

}
