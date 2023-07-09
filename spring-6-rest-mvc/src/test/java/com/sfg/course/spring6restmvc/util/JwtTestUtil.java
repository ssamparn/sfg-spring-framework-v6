package com.sfg.course.spring6restmvc.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtTestUtil {

    public static final SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor jwtRequestPostProcessor =
            jwt().jwt(jwt -> jwt.claims(claims -> {
                        claims.put("scope", "message-read");
                        claims.put("scope", "message-write");
                    })
                    .subject("messaging-client")
                    .notBefore(Instant.now().minusSeconds(5l)));
}
