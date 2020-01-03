package com.lemon.api;

import com.lemon.entity.Device;
import com.lemon.layui.LayuiTableData;
import com.lemon.response.R;
import com.lemon.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author dengqiao
 * @date 2019-12-19 16:47
 */
@Slf4j
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping("/list")
    public LayuiTableData<Device> list(Device devices, @RequestParam int page, @RequestParam int limit) {
        try {
            Page<Device> pageData = deviceService.findPage(devices, page - 1, limit);
            LayuiTableData<Device> layuiTableData = new LayuiTableData<>(pageData.getTotalElements(), pageData.getContent());
            return layuiTableData;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new LayuiTableData<>(1, "数据加载出错");
    }

    @RequestMapping("/delete")
    public R delete(@RequestParam("ids") String ids) {
        try {
            String[] idArray = ids.split(",");
            int i = 0;
            for (String id : idArray) {
                long returnFlag = deviceService.deleteById(id);
                if (returnFlag > 0) {
                    i++;
                }
            }
            if (i == idArray.length) {
                return R.OK;
            } else {
                return R.ok("警告:您删除了" + i + "条记录，有" + (idArray.length - i) + "条记录删除失败！");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return R.FAIL;
    }

    @RequestMapping("/save")
    public R save(Device device) {
        try {
            device.setBrokerUsername(device.getProductName() + "/" + device.getDeviceName());
            List<Device> devicesList = deviceService.findByBrokerUsername(device.getBrokerUsername());
            if (devicesList != null && devicesList.size() > 0) {
                return R.error(device.getBrokerUsername() + "已经存在");
            } else {
                deviceService.save(device);
                return R.ok();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return R.error();
    }


}
