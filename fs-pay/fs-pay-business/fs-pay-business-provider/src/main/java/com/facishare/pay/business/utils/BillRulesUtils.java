package com.facishare.pay.business.utils;

import com.facishare.pay.business.model.result.BillRuleResult;
import com.facishare.pay.business.model.vo.BillruleVO;

import java.math.BigDecimal;

/**
 * Created by wangtao on 2015/10/26.
 */
public class BillRulesUtils {

    public static BillRuleResult getBillRule(BillruleVO billruleVO) {
        return new BillRuleResult(BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
