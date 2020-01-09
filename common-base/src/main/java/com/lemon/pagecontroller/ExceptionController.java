package com.lemon.pagecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController {

    @RequestMapping(value = "/404")
    public String error404() {
        return "/error/404";
    }

    @RequestMapping(value = "/500")
    public String error500() {
        return "/error/500";
    }

    @RequestMapping(value = "/403")
    public String error403() {
        return "/error/403";
    }

    @RequestMapping(value = "/401")
    public String error401() {
        return "/error/401";
    }

}
