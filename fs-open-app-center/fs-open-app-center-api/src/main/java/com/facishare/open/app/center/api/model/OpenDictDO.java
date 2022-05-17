package com.facishare.open.app.center.api.model;

/**
 * 应用中心字典映射对象
 *
 * @author zenglb
 * @date 2015年8月17日
 */
public class OpenDictDO extends BaseDO {

    private static final long serialVersionUID = -6717667340404772673L;

    /**
     * 类型
     */
    private String dictType;

    /**
     * key
     */
    private String dictKey;

    /**
     * value
     */
    private String dictValue;

    /**
     * 序号，一般用于前端下拉进行排序
     */
    private Integer orderNo;

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dictKey == null) ? 0 : dictKey.hashCode());
        result = prime * result + ((dictType == null) ? 0 : dictType.hashCode());
        result = prime * result + ((dictValue == null) ? 0 : dictValue.hashCode());
        result = prime * result + ((orderNo == null) ? 0 : orderNo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OpenDictDO other = (OpenDictDO) obj;
        if (dictKey == null) {
            if (other.dictKey != null)
                return false;
        } else if (!dictKey.equals(other.dictKey))
            return false;
        if (dictType == null) {
            if (other.dictType != null)
                return false;
        } else if (!dictType.equals(other.dictType))
            return false;
        if (dictValue == null) {
            if (other.dictValue != null)
                return false;
        } else if (!dictValue.equals(other.dictValue))
            return false;
        if (orderNo == null) {
            if (other.orderNo != null)
                return false;
        } else if (!orderNo.equals(other.orderNo))
            return false;
        return true;
    }
}
