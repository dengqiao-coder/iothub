package com.lemon.devicesdk.config;

import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * @author dengqiao
 * @date 2019-07-24 18:18
 */

@Configuration
@IntegrationComponentScan
public class MqttConfig {

    @Autowired
    private Config config;

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(config.getProductKey() + "/" + config.getMqttClientCode());
        mqttConnectOptions.setPassword(config.getPassword().toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{config.getHostUrl()});
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setKeepAliveInterval(3);
        mqttConnectOptions.setCleanSession(true);
        return mqttConnectOptions;
    }

    @Bean
    public MqttClientPersistence getMqttClientPersistence() {
        MqttClientPersistence mqttClientPersistence = new MqttDefaultFilePersistence();
        return mqttClientPersistence;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        factory.setPersistence(getMqttClientPersistence());
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(config.getProductKey() + "/" + config.getMqttClientCode(), mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultRetained(true);
        messageHandler.setDefaultQos(2);
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
}
