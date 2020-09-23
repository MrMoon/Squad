package com.squad.gateway.config.security.utils;

import javax.validation.constraints.NotNull;

public class JwtBase64 {
    @NotNull
    public static String toBase64(@NotNull String str) {
        return java.util.Base64.getEncoder().encodeToString(str.getBytes());
    }
}
