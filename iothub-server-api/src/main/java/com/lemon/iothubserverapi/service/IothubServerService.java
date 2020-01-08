package com.lemon.iothubserverapi.service;

import com.lemon.entity.DeviceVo;
import com.lemon.response.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author dengqiao
 * @date 2020-01-08 18:31
 */
@FeignClient(name = "iothub-server")
public interface IothubServerService {
    @RequestMapping("/device/register/{productName}")
    R register(@PathVariable("productName") String productName);

    @RequestMapping("/device/findByBrokerUsername/{productName}/{deviceName}")
    DeviceVo findByBrokerUsername(@PathVariable("productName") String productName, @PathVariable("deviceName") String deviceName);

    @RequestMapping("/device/findByProductName/{productName}")
    List<DeviceVo> findByProductName(@PathVariable("productName") String productName);
}
