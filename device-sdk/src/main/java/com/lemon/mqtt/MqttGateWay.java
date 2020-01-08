package com.lemon.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author dengqiao
 * @date 2019-07-24 18:23
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateWay {
    void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);
}
