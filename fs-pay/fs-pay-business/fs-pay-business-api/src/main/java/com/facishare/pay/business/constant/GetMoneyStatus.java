package com.facishare.pay.business.constant;

import java.util.Arrays;
import java.util.List;

import com.facishare.pay.common.utils.BaseEnum;
import com.facishare.pay.common.utils.EnumUtils;

/**
 * 用户状态
 * @author guom
 * */
public enum GetMoneyStatus implements BaseEnum<GetMoneyStatus> {
    
    AUDITING(1, "待审核"),
    AUDITED(2, "已审核"),
    TELLER(3, "已出纳"),
    FINISH(4, "已提现"),
    NO_PASS(5, "不通过");
    
    private int index;
    
    private String description;
    
    private GetMoneyStatus(int index, String description) {
        this.index = index;
        this.description = description;
    }

    public static GetMoneyStatus findByIndex(int index) {
        return EnumUtils.getEnum(GetMoneyStatus.class, index);
    }
    
    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }
    
    public boolean equals(GetMoneyStatus getMoneyStatus) {
        if (this.getIndex() == getMoneyStatus.getIndex()) {
            return true;
        }
        return false;
    }
    
    public static List<GetMoneyStatus> getAll() {
        return Arrays.asList(GetMoneyStatus.class.getEnumConstants());
    }
}
