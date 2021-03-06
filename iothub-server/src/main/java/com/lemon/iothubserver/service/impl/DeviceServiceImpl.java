package com.lemon.iothubserver.service.impl;

import cn.hutool.core.util.StrUtil;
import com.lemon.iothubserver.dao.DeviceDao;
import com.lemon.iothubserver.entity.Device;
import com.lemon.iothubserver.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dengqiao
 * @date 2019-12-19 16:54
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    @Override
    public Page<Device> findPage(Device devices, int pageNo, int pageSize) {
        Criteria criteria = null;
        if (devices != null && StrUtil.isNotBlank(devices.getProductName())) {
            criteria = deviceDao.queryLike(criteria, "product_name", devices.getProductName());
        }
        if (devices != null && StrUtil.isNotBlank(devices.getDeviceName())) {
            criteria = deviceDao.queryLike(criteria, "device_name", devices.getDeviceName());
        }
        return deviceDao.findPage(criteria, pageNo, pageSize, Sort.Direction.DESC, "update_time");
    }

    @Override
    public long deleteById(String id) {
        return deviceDao.deleteById(id);
    }

    @Override
    public List<Device> findByBrokerUsername(Device device) {
        Criteria criteria = Criteria.where("broker_username").is(device.getBrokerUsername());
        if (StrUtil.isNotBlank(device.getId())) {
            criteria.and("id").ne(device.getId());
        }
        return deviceDao.findList(criteria);
    }

    @Override
    public Device findByBrokerUsername(String brokerUsername) {
        return deviceDao.findOne("broker_username", brokerUsername);
    }

    @Override
    public List<Device> findByProductName(String productName) {
        return deviceDao.findList("product_name", productName);
    }

    @Override
    public void save(Device devices) {
        devices.preSave();
        deviceDao.save(devices);
    }

    @Override
    public Device findById(String id) {
        return deviceDao.findById(id);
    }
}
