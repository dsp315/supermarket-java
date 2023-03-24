package com.tengke.supermarket.mapper;

import com.tengke.supermarket.model.Goods;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: cgs
 * @Description： 商品操作接口
 * @Date:Created in 17:17 2020/7/26
 */
@Component
public interface GoodsMapper {


    /**
     * 分页查询商品信息,并且不显示停产商品
     * @return 商品信息列表
     */
    List<Goods> selectGoodsList();

    /**
     * 查询商品信息总数
     * @return 总数
     */
    int countGoods();

    /**
     * 根据商品编号查找商品信息
     * @param gdsId 商品编号
     * @return 商品信息
     */
    Goods selectGoodsById(int gdsId);

    /**
     * 通过商品名字查找商品编号
     * @param name 商品名称
     * @return 商品编号
     */
    List<Integer> selectIdByName(String name);

    /**
     * 增加商品信息
     * @param goods 需要增加的商品信息
     * @return 行数
     */
    int addGoods(Goods goods);

    /**
     * 修改商品信息
     * @param goods 要修改的商品新信息
     * @return
     */
    int updateGoods(Goods goods);


    /**
     * 通过商品编号查找商品名字
     * @param gdsId 商品编号
     * @return 商品名称
     */
    String selectNameById(int gdsId);

    int addAmount(Integer gdsId, int amount);

    int updateGoodsPriceAndAmountById(Integer gdsId, Float price, Integer amount);

    List<Goods> selectGoods(String id);
}
