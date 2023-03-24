package com.tengke.supermarket.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Purchase {
    /**
     * 进货记录编号
     */
    private Integer purchaseId;
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
