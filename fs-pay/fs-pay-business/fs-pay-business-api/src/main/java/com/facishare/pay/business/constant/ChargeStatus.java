package com.facishare.pay.business.constant;

import java.util.Arrays;
import java.util.List;

import com.facishare.pay.common.utils.BaseEnum;
import com.facishare.pay.common.utils.EnumUtils;

/**
 * 充值状态
 * @author guom
 * */
public enum ChargeStatus implements BaseEnum<ChargeStatus> {

    INIT_CHARGE(0, "初始化"),
    SUCCESS_CHARGE(1, "充值成功"),
    FAILURE_CHARGE(2, "充值失败");
    
    private int index;
    
    private String description;
    
    private ChargeStatus(int index, String description) {
        this.index = index;
        this.description = description;
    }

    public static ChargeStatus findByIndex(int index) {
        return EnumUtils.getEnum(ChargeStatus.class, index);
    }
    
    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }
    
    public boolean equals(ChargeStatus chargeStatus) {
        if (this.getIndex() == chargeStatus.getIndex()) {
            return true;
        }
        return false;
    }
    
    public static List<ChargeStatus> getAll() {
        return Arrays.asList(ChargeStatus.class.getEnumConstants());
    }
}
