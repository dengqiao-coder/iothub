package com.lemon.iothubserverapi.controller;

import com.lemon.entity.DeviceVo;
import com.lemon.iothubserverapi.service.IothubServerService;
import com.lemon.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dengqiao
 * @date 2020-01-08 15:33
 */
@RestController
public class DeviceController {

    @Autowired
    private IothubServerService iothubServerService;

    @RequestMapping("/register/{productName}")
    public R register(@PathVariable String productName) {
        return iothubServerService.register(productName);
    }

    @RequestMapping("/queryByProductName/{productName}")
    public List<DeviceVo> queryByProductName(@PathVariable String productName) {
        return iothubServerService.findByProductName(productName);
    }

    @RequestMapping("/queryByBrokerUsername/{productName}/{deviceName}")
    public DeviceVo queryByBrokerUsername(@PathVariable String productName, @PathVariable String deviceName) {
        return iothubServerService.findByBrokerUsername(productName, deviceName);
    }

}
