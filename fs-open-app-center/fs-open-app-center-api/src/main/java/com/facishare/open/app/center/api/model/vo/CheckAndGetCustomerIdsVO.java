package com.facishare.open.app.center.api.model.vo;

import com.facishare.open.app.center.api.constants.CommonConstant;

import java.io.Serializable;
import java.util.List;

/**
 * 企信检查校验客服回复消息的接口返回。
 * Created by zenglb on 2016/12/20.
 */
public class CheckAndGetCustomerIdsVO implements Serializable {
    private static final long serialVersionUID = 525024110871986583L;

    /**
     * 是否消息可以上行.1,可以上行，2,不可上行消息.
     */
    private Integer status;

    /**
     * 是否需要一更新客服列表. 1,需要更新， 2, 不更新。
     */
    private Integer customerListChangeStatus;

    /**
     * 最新的客服列表.
     */
    private List<Integer> customerIds;

    public CheckAndGetCustomerIdsVO() {
        this(null, null);
    }

    public CheckAndGetCustomerIdsVO(Integer status) {
        this(status, null);
    }

    public CheckAndGetCustomerIdsVO(Integer status, List<Integer> customerIds) {
        this.status = status;
        this.customerListChangeStatus = CommonConstant.NO;
        if (null != customerIds) {
            this.customerListChangeStatus = CommonConstant.YES;
        }
        this.customerIds = customerIds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCustomerListChangeStatus() {
        return customerListChangeStatus;
    }

    public void setCustomerListChangeStatus(Integer customerListChangeStatus) {
        this.customerListChangeStatus = customerListChangeStatus;
    }

    public List<Integer> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Integer> customerIds) {
        this.customerIds = customerIds;
    }

    @Override
    public String toString() {
        return "CheckAndGetCustomerIdsVO{" +
                "status=" + status +
                ", customerListChangeStatus=" + customerListChangeStatus +
                ", customerIds=" + customerIds +
                '}';
    }
}
