package com.tengke.supermarket.controller;

import com.alibaba.druid.util.StringUtils;
import com.tengke.supermarket.dto.ResultDTO;
import com.tengke.supermarket.model.Supplier;
import com.tengke.supermarket.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author cgs
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;


    @GetMapping("/search/{id}")
    public ResultDTO searchSupplierById(@PathVariable("id") int id) {
        return supplierService.searchSupplierById(id);
    }

    @GetMapping("/{pageNo}/{size}")
    public ResultDTO searchAllSupplier(@PathVariable("pageNo") int pageNo, @PathVariable("size") int size) {
        return supplierService.searchAllSupplier(pageNo, size);
    }
    @PostMapping("/addSupplier")
    public ResultDTO addSupplier (@RequestBody Supplier supplier){
        if (checkSupplier(supplier.getSpName(),supplier.getSpTel())){
            return ResultDTO.error("名称和联系方式不能为空！");
        }
        try {
            return supplierService.addSupplier(supplier);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.error("系统繁忙，请稍后重试");
        }
    }


    public boolean checkSupplier(String name, String tel){
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(tel)){
            return true;
        }else {
            return false;
        }
    }
}
