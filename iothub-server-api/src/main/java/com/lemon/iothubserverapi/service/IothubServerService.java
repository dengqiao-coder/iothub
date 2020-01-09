package com.lemon.iothubserverapi.service;

import com.lemon.entity.DeviceVo;
import com.lemon.response.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author dengqiao
 * @date 2020-01-08 18:31
 */
@FeignClient(name = "iothub-server")
public interface IothubServerService {
    @RequestMapping("/device/register")
    R register(@RequestParam("productName") String productName);

    @RequestMapping("/device/findByBrokerUsername")
    DeviceVo findByBrokerUsername(@RequestParam("productName") String productName, @RequestParam("deviceName") String deviceName);

    @RequestMapping("/device/findByProductName")
    List<DeviceVo> findByProductName(@RequestParam("productName") String productName);
}
