package com.lemon.pagecontroller;

import com.lemon.entity.Device;
import com.lemon.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author dengqiao
 * @date 2019-12-19 16:46
 */
@Controller
@RequestMapping("/page/device")
public class DevicePageController {
    @Autowired
    private DeviceService deviceService;

    @RequestMapping("/index")
    public String index() {
        return "device/index";
    }

    @RequestMapping("/add")
    public String add() {
        return "device/add";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam String id, Model model) {
        Device device = deviceService.findById(id);
        model.addAttribute("device", device);
        return "device/edit";
    }
}
