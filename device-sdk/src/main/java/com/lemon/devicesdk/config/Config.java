package com.lemon.devicesdk.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author dengqiao
 * @date 2019-07-26 11:31
 */
@Component
@Data
public class Config {

    @Value("${spring.mqtt.productKey}")
    private String productKey;

    @Value("${spring.mqtt.mqttClientCode}")
    private String mqttClientCode;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.url}")
    private String hostUrl;

}
