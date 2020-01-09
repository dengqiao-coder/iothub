package com.lemon.pagecontroller;

import com.lemon.mqtt.MqttGateWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengqiao
 * @date 2020-01-07 18:01
 */
@RestController
public class TestController {
    @Autowired
    private MqttGateWay mqttGateWay;

    @RequestMapping("/send")
    public void send(){
        mqttGateWay.sendToMqtt("123","/123");
    }
}
