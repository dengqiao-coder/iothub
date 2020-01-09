package com.lemon.iothubserverapi.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lemon.iothubserverapi.entity.Token;
import com.lemon.commonutils.util.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author dengqiao
 * @date 2020-01-08 11:11
 */
@Slf4j
@RestController
public class JwtController {
    private static final long EXPIRE_TIME = 15 * 60 * 1000;
    private static final String TOKEN_SECRET = "lemon";

    @RequestMapping("/getToken")
    public Token getToken() {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String username = IdWorker.getRandomSeq();
            String password = JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
            Token token = new Token();
            token.setUsername(username);
            token.setPasswork(password);
            return token;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
