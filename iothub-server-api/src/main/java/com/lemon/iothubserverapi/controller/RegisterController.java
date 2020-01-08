package com.lemon.iothubserverapi.controller;

import com.lemon.iothubserverapi.service.IothubServerService;
import com.lemon.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengqiao
 * @date 2020-01-08 15:33
 */
@RestController
public class RegisterController {

    @Autowired
    private IothubServerService iothubServerService;

    @RequestMapping("/register/{productName}")
    public R register(@PathVariable String productName) {
        return iothubServerService.register(productName);
    }
}
