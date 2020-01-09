package com.lemon.iothubserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author dengqiao
 * @date 2019-08-02 11:02
 */
@Slf4j
@RestController
public class MqttController {
    @RequestMapping("/mqtt")
    public String proccessMqttMessage(HttpServletRequest request) {
        log.info(getParm(request));
        return "ok";
    }

    public String getParm(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return sb.toString();
    }
}
