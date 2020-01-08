package com.lemon.iothubserverapi.service;

import com.lemon.response.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dengqiao
 * @date 2020-01-08 18:31
 */
@FeignClient(name = "iothub-server")
public interface IothubServerService {
    @RequestMapping("/device/register/{productName}")
    R register(@PathVariable("productName") String productName);
}
