package com.tengke.supermarket.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class PurchaseRecordDTO {
    /**
     * 进货记录编号
     */
    private Integer purchaseId;
    /**
     * 商品编号
     */
    private Integer goodsId;
    /**
     * 进货单价
     */
    private Float price;
    /**
     * 进货数量
     */
    private Integer amount;
    /**
     * 供应商编号
     */
    private Integer supplierId;
    /**
     * 员工编号
     */
    private Integer staffId;
    /**
     * 进货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date purchaseDate;
}
