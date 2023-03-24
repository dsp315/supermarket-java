package com.tengke.supermarket.controller;

import com.alibaba.druid.util.StringUtils;
import com.tengke.supermarket.dto.ResultDTO;
import com.tengke.supermarket.model.Goods;
import com.tengke.supermarket.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cgs
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/{pageNo}/{size}")
    public ResultDTO findGoodsLess(@PathVariable("pageNo") int pageNo, @PathVariable("size") int size) {
        return goodsService.showGoodsList(pageNo,size);
    }

    @GetMapping("/search/{id}")
    public ResultDTO searchGoodsById(@PathVariable("id") int id) {
        return goodsService.searchGoodsById(id);
    }

    @PostMapping("/addGoods")
    public ResultDTO addGoods(@RequestBody Goods goods){
        try {
            return goodsService.addGoods(goods);
        }catch (Exception e){
            e.printStackTrace();
            return ResultDTO.error("系统繁忙，请稍后重试");
        }
    }
    public boolean checkGoods(String name, String id){
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(id)){
            return true;
        }else {
            return false;
        }
    }
}
