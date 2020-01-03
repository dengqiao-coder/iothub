package com.lemon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dengqiao
 * @date 2019-12-19 16:46
 */
@Controller
@RequestMapping("/page/device")
public class DevicePageController {
    @RequestMapping("/index")
    public String index() {
        return "device/index";
    }

    @RequestMapping("/add")
    public String add() {
        return "device/add";
    }
}
