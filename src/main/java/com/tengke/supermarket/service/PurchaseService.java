package com.tengke.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.tengke.supermarket.dto.*;
import com.tengke.supermarket.mapper.*;
import com.tengke.supermarket.model.Goods;
import com.tengke.supermarket.model.Purchase;
import com.tengke.supermarket.model.PurchaseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 彤老板
 */
@Service
public class PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private PurchaseRecordMapper purchaseRecordMapper;


    /**
     * 进货处理，添加进货记录，修改商品库存
     * @param dto 进货信息
     * @return 提示信息
     */
    public ResultDTO purchase(PurchaseRecordDTO dto) {
        //不能提交空的进货项
        if(dto == null) {
            return ResultDTO.error("请添加进货记录");
        }
        try {
            Goods goods = goodsMapper.selectGoodsById(dto.getGoodsId());
            //修改商品库存信息
            int amount = goods.getAmount() + dto.getAmount();
            //更新数据库记录
            goodsMapper.addAmount(goods.getGdsId(),amount);
            Purchase purchase = new Purchase();
            purchase.setSupplierId(dto.getSupplierId());
            purchase.setStaffId(dto.getStaffId());
            purchase.setPurchaseDate(new Date());
            purchaseMapper.addPurchase(purchase);
            int pId =purchase.getPurchaseId();
            PurchaseRecord record = new PurchaseRecord();
            record.setPurchaseId(pId);
            record.setGoodsId(dto.getGoodsId());
            record.setAmount(dto.getAmount());
            record.setPrice(dto.getPrice());
            purchaseRecordMapper.addRecord(record);
            return ResultDTO.success("进货成功");
        }catch (Exception e){
            return ResultDTO.error("操作失败");
        }
    }

    /**
     * 分页展示进货记录
     * @return 进货记录列表
     */
    public ResultDTO showPurRecordByPage(int page, int size,String goodsName) {
        List<Integer> goodsIdList = goodsMapper.selectIdByName(goodsName);
        //如果不存在该名字的商品
        if(goodsIdList.size() == 0) {
            return ResultDTO.error("无该商品");
        }
        PageHelper.startPage(page,size);
        List<PurRecordResult> list = purchaseRecordMapper.selectPurchaseRecordList(goodsName);
        Map<String,Object> map = new HashMap<>(2);
        map.put("list",list);
        map.put("recordsNum",list.size());
        return ResultDTO.success(list);
    }



    /**根据进货编号查询进货记录
     * @param purchaseId 进货记录编号
     * @return 一条对应编号的进货记录，用List存储
     */
    public List<PurchaseRecord> showPurRecordById(int purchaseId) {
        return purchaseMapper.selectPurRecordById(purchaseId);
    }
}
