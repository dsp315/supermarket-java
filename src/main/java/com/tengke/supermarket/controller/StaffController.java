package com.tengke.supermarket.controller;

import com.alibaba.druid.util.StringUtils;
import com.tengke.supermarket.dto.PageDTO;
import com.tengke.supermarket.dto.ResultDTO;
import com.tengke.supermarket.dto.StaffDTO;
import com.tengke.supermarket.model.Staff;
import com.tengke.supermarket.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Mr.Chen
 * @Description:
 * @Date:Created in 6:09 2020/7/28
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping("/basicInfo")
    private ResultDTO getAllEmployeeByPage(HttpServletRequest request) {
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer size = Integer.parseInt(request.getParameter("size"));
        String search = request.getParameter("search");
        return staffService.getAllStaffByPage(page, size, search);
    }

    @PostMapping("/addStaff")
    private ResultDTO addStaff(@RequestBody Staff staff) {
        if (checkStaff(staff.getSfName(),staff.getIdentityId())){
            return ResultDTO.error("姓名和身份证号不能为空！");
        }
        try {
            return staffService.addStaff(staff);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.error("系统繁忙，请稍后重试");
        }
    }
    @PostMapping("/editStaff")
    private ResultDTO editStaff(@RequestBody Staff staff) {
        if (checkStaff(staff.getSfName(),staff.getIdentityId())){
            return ResultDTO.error("姓名和身份证号不能为空！");
        }
        try {
            return staffService.editStaff(staff);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.error("系统繁忙，请稍后重试");
        }
    }

    public boolean checkStaff(String name, String id){
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(id)){
            return true;
        }else {
            return false;
        }
    }
}
