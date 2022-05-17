package com.facishare.pay.business.constant;

import java.util.Arrays;
import java.util.List;

import com.facishare.pay.common.utils.BaseEnum;
import com.facishare.pay.common.utils.EnumUtils;

public enum OperateType implements BaseEnum<OperateType> {

    //提现流水
    // 申请
    GM_CREATE_ORDER(101, "生成订单"),
    GM_CREATE_APPLY(102, "生成提现申请"),
    GM_FREEZE_MONEY(103, "冻结金额——转审批"),
    // 审批
    GM_START_AUDITOR(104, "开始审批"),
    GM_NOPASS_AUDITOR_UNFREEZE(105, "审批不通过——解冻"),
    GM_UPDATE_AUDITOR(106, "修改审批状态——结束"),
    // 出纳
    GM_START_TELLER(107, "准备出纳"),
    GM_NOPASS_TELLER_UNFREEZE(108, "出纳不通过——解冻"),
    GM_UPDATE_TELLER(109, "修改出纳状态——结束"),
    
    GM_HAS_DEPOSIT(110, "已提现"),
    GM_FREEZE_TO_USE(111, "冻结资金——转已用"),
    GM_UPDATE_APPLY_FINISH(112, "申请——转已提现"),
    GM_UPDATE_ORDER_FINISH(113, "订单——转已完成"),
    
    
    //充值
    CH_CREATE_ORDER(201, "生成订单"),
    CH_CREATE_CHARGE(202, "生成充值流水"),
    CH_SEND_THIRD(203, "调用了第三方接口"),
    CH_BACK_THIRD(204, "第三方接口回调"),
    CH_WALLET_CHARGE(205, "钱包充值"),
    CH_UPDATE_CHARGE(206, "修改流水状态"),
    CH_UPDATE_ORDER(207, "修改订单——已完成"),
    
    // 转账
    TR_CREATE_ORDER(301, "生成订单"),
    TR_CREATE_TRANSFER(302, "生成转账流水"),
    TR_FREEZE_MONEY(303, "冻结金额"),

    TR_TRANSFER_IN(304, "转账"),
    TR_TRANSFER_MONEY(305, "转账"),
    TR_UPDATE_TRANSFER(306, "修改流水"),
    TR_UPDATE_ORDER(307, "修改订单——已完成"),
    ;
    
    /**
     * 序号
     */
    protected int index;
    
     /** 
      * 银行名称
      */
    protected String description;

    private OperateType(int index, String description) {
        this.index = index;
        this.description = description;
    }
    
    public static OperateType findByIndex(int index) {
        return EnumUtils.getEnum(OperateType.class, index);
    }
    
    public boolean equals(OperateType bankType) {
        if (this.getIndex() == bankType.getIndex()) {
            return true;
        }
        return false;
    }
    
    public static List<OperateType> getAll() {
        return Arrays.asList(OperateType.class.getEnumConstants());
    }    
    
    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getDescription() {
        return description;
    }


}
