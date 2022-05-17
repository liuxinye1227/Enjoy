package com.facishare.pay.business.constant;

import java.util.Arrays;
import java.util.List;

import com.facishare.pay.common.utils.BaseEnum;
import com.facishare.pay.common.utils.EnumUtils;

public enum BankType implements BaseEnum<BankType> {

    OTHER(1,"OTHER", "其它银行"),
    GSYH(2,"GSYH", "中国工商银行"),
    ZSYH(3,"ZSYH", "招商银行"),
    JSYH(4,"JSYH", "中国建设银行"),
    JTYH(5,"JTYH","交通银行"),
    NYYH(6,"NYYH","中国农业银行");
    /**
     * 序号
     */
    protected int index;
     /** 
      * 内部银行代码 
      */
    protected String bankCode;
    
     /** 
      * 银行名称
      */
    protected String bankName;

    private BankType(int index, String bankCode, String bankName) {
        this.index = index;
        this.bankCode = bankCode;
        this.bankName = bankName;
    }
    
    public static BankType findByIndex(int index) {
        return EnumUtils.getEnum(BankType.class, index);
    }
    
    public boolean equals(BankType bankType) {
        if (this.getIndex() == bankType.getIndex()) {
            return true;
        }
        return false;
    }
    
    public static List<BankType> getAll() {
        return Arrays.asList(BankType.class.getEnumConstants());
    }    
    
    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getDescription() {
        return bankName;
    }

}
