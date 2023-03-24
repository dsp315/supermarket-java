package com.tengke.supermarket.service;

import com.github.pagehelper.PageHelper;
import com.tengke.supermarket.dto.PageDTO;
import com.tengke.supermarket.dto.ResultDTO;
import com.tengke.supermarket.mapper.GoodsMapper;
import com.tengke.supermarket.mapper.SellItemMapper;
import com.tengke.supermarket.mapper.SellRecordMapper;
import com.tengke.supermarket.mapper.StaffMapper;
import com.tengke.supermarket.model.Goods;
import com.tengke.supermarket.model.SellItem;
import com.tengke.supermarket.model.SellRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cgs
 */
@Service
public class SellService {
    @Autowired
    private SellItemMapper sellItemMapper;
    @Autowired
    private SellRecordMapper sellRecordMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StaffMapper staffMapper;
    /**
     * 查看商品编号是否有对应商品存在，且商品库存是否充足
     * @param id 商品编号
     * @param amount 库存
     * @return 如果商品存在，则返回商品，否则返回null
     */
    public Goods existAndEnough(int id, int amount) {
        Goods goods = goodsMapper.selectGoodsById(id);
        if(goods!=null) {
            if(amount <= goods.getAmount()) {
                return goods;
            }
        }
        return null;
    }

    /**
     * 销售处理，需要添加销售记录，添加销售项，修改商品库存
     * @param item 销售项集合
     * @return 提示信息
     */
    public ResultDTO sell(SellItem item,int sfId) {
        Goods goods = goodsMapper.selectGoodsById(item.getGdsId());
        if (goods.getAmount()<item.getAmount()){
            return ResultDTO.error("库存不足！");
        }
        if (goods.getPrice()>item.getPrice()){
            return ResultDTO.error("售价低于进价");
        }
        Integer amount = goods.getAmount()- item.getAmount();
        int i = goodsMapper.updateGoodsPriceAndAmountById(item.getGdsId(),item.getPrice(),amount);
        if (i>0){
            SellRecord record = new SellRecord();
            record.setSfId(sfId);
            record.setSellDate(new Date());
            sellRecordMapper.addSellRecord(record);
            Integer sellId = record.getSellId();
            item.setSellId(sellId);
            sellItemMapper.addItems(item);
            return ResultDTO.success("添加成功！");
        }
        return ResultDTO.error("添加失败！");

    }

    /**
     * 分页查询销售记录
     * @param pageNo 页码
     * @param size 页大小
     * @return 销售记录列表
     */
    public ResultDTO showSellRecordList(int pageNo, int size) {
        PageHelper.startPage(pageNo,size);
        List<SellRecord> records = sellRecordMapper.selectRecordsByPages();
        Map<String,Object> map = new HashMap<>(2);
        map.put("data",records);
        map.put("recordsNum",records.size());
        return ResultDTO.success("查询成功",map);
    }

    /**
     * 根据销售编号查找销售项
     * @param sellId 销售编号
     * @return 销售项列表
     */
    public ResultDTO showSellItem(int sellId) {

        return ResultDTO.success("查询成功",sellItemMapper.selectAllItemsById(sellId));
    }
}
