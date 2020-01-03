package com.lemon.dao;

import com.lemon.entity.Device;
import com.lemon.mongo.MongoDao;
import org.springframework.stereotype.Component;

/**
 * @author dengqiao
 * @date 2019-12-19 17:18
 */
@Component
public class DeviceDao extends MongoDao<Device> {
}
