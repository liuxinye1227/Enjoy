package com.facishare.pay.business.model.result;

import java.math.BigDecimal;

/**
 * Created by wangtao on 2015/10/26.
 */
public class BillRuleResult  {

    private BigDecimal sysFee = BigDecimal.ZERO;

    private BigDecimal actualFee = BigDecimal.ZERO;

    public BillRuleResult() {
    }

    public BillRuleResult(BigDecimal sysFee, BigDecimal actualFee) {
        this.sysFee = sysFee;
        this.actualFee = actualFee;
    }

    public BigDecimal getSysFee() {
        return sysFee;
    }

    public void setSysFee(BigDecimal sysFee) {
        this.sysFee = sysFee;
    }

    public BigDecimal getActualFee() {
        return actualFee;
    }

    public void setActualFee(BigDecimal actualFee) {
        this.actualFee = actualFee;
    }
}
