package com.tengke.supermarket.dto;

import lombok.Data;

@Data
public class PurRecordResult {
    private String purchaseId;//记录编号
    private String goodsName;//商品名称
    private String price;//进货单价
    private String amount;//进货数量
    private String purchaseDate;//进货日期
    private String supplierId;//供货商id
    private String staffId;//进货人员
}
