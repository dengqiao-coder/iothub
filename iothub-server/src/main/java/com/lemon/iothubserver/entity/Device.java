package com.lemon.iothubserver.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author dengqiao
 * @date 2019-08-13 14:18
 */
@Data
public class Device extends MongoAbsEntity {

    @Field("product_name")
    private String productName;
    @Field("device_name")
    private String deviceName;
    @Field("broker_username")
    private String brokerUsername;
    @Field("secret")
    private String secret;
}
