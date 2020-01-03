package com.lemon.service;

import com.lemon.entity.Device;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author dengqiao
 * @date 2019-12-19 16:54
 */
public interface DeviceService {
    Page<Device> findPage(Device devices, int pageNo, int pageSize);

    long deleteById(String id);

    List<Device> findByBrokerUsername(String brokerUsername);

    void save(Device devices);
}
