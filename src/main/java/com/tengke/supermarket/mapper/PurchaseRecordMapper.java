package com.tengke.supermarket.mapper;

import com.tengke.supermarket.dto.PurRecordResult;
import com.tengke.supermarket.dto.PurchaseRecordDTO;
import com.tengke.supermarket.model.PurchaseRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PurchaseRecordMapper {

    int addRecord(PurchaseRecord record);

    List<PurRecordResult> selectPurchaseRecordList(String goodsName);
}
