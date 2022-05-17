package com.facishare.pay.business.model.result;

import java.io.Serializable;

/**
 * Created by wangtao on 2015/10/23.
 */
public class TransferResult implements Serializable {

    private String orderNo;

    public TransferResult(String orderNo) {
        this.orderNo = orderNo;
    }

    public TransferResult() {
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
