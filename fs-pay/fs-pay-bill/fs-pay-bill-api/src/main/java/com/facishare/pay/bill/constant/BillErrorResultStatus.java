package com.facishare.pay.bill.constant;

import java.util.Arrays;
import java.util.List;

import com.facishare.pay.common.utils.BaseEnum;
import com.facishare.pay.common.utils.EnumUtils;

public enum BillErrorResultStatus implements BaseEnum<BillErrorResultStatus> {

    UNFINISH(1, "未处理"),
    FINISH(2, "已处理");
    
    private int index;
    
    private String description;
    
    private BillErrorResultStatus(int index, String description) {
        this.index = index;
        this.description = description;
    }

    public static BillErrorResultStatus findByIndex(int index) {
        return EnumUtils.getEnum(BillErrorResultStatus.class, index);
    }
    
    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }
    
    public boolean equals(BillErrorResultStatus billResultStatus) {
        if (this.getIndex() == billResultStatus.getIndex()) {
            return true;
        }
        return false;
    }
    
    public static List<BillErrorResultStatus> getAll() {
        return Arrays.asList(BillErrorResultStatus.class.getEnumConstants());
    }
}
