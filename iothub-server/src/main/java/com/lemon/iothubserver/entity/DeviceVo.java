package com.lemon.iothubserver.entity;

import lombok.Data;

/**
 * @author dengqiao
 * @date 2020-01-08 20:11
 */
@Data
public class DeviceVo {
    private String productName;
    private String deviceName;
    private String secret;
}
