package com.facishare.pay.business.constant;

import java.util.Arrays;
import java.util.List;

import com.facishare.pay.common.utils.BaseEnum;
import com.facishare.pay.common.utils.EnumUtils;

/**
 * 充值状态
 * @author guom
 * */
public enum TransferStatus implements BaseEnum<TransferStatus> {

    IN_TRANSFER(1, "转账中"),
    FINISH_TRANSFER(2, "转账完成"),
    FAIL_TRANSFER(3, "转账失败"),
    OUTTIME_TRANSFER(4, "转账过期");

    private int index;
    
    private String description;

    private TransferStatus(int index, String description) {
        this.index = index;
        this.description = description;
    }
    
    public boolean equals(TransferStatus transferStatus) {
        if (this.getIndex() == transferStatus.getIndex()) {
            return true;
        }
        return false;
    }
    
    public static List<TransferStatus> getAll() {
        return Arrays.asList(TransferStatus.class.getEnumConstants());
    }
    
    public static TransferStatus findByIndex(int index) {
        return EnumUtils.getEnum(TransferStatus.class, index);
    }
    
    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }
    
    
    
}
