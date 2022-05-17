package com.facishare.pay.business.constant;

import java.util.Arrays;
import java.util.List;

import com.facishare.pay.common.utils.BaseEnum;
import com.facishare.pay.common.utils.EnumUtils;

/**
 * 用户状态
 * @author guom
 * */
public enum UserStatus implements BaseEnum<UserStatus> {
    
    ENABLED(1, "启用"),
    
    DISABLED(0, "禁用");
    
    private int index;
    
    private String description;
    
    private UserStatus(int index, String description) {
        this.index = index;
        this.description = description;
    }

    public static UserStatus findByIndex(int index) {
        return EnumUtils.getEnum(UserStatus.class, index);
    }
    
    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }
    
    public boolean equals(UserStatus userStatus) {
        if (this.getIndex() == userStatus.getIndex()) {
            return true;
        }
        return false;
    }
    
    public static List<UserStatus> getAll() {
        return Arrays.asList(UserStatus.class.getEnumConstants());
    }
}
