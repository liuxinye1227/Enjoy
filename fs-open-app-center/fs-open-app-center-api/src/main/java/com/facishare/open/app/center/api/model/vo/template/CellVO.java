package com.facishare.open.app.center.api.model.vo.template;

import java.io.Serializable;

import com.facishare.open.app.center.api.constants.CommonConstant;

/**
 * 关键词
 *
 * @author songk
 * @date 2015年8月28日
 */
public class CellVO implements Serializable {

    private static final long serialVersionUID = 5114425542102707684L;

    /**
     * 关键词名
     */
    private String cellName;

    /**
     * 关键词值
     */
    private String cellValue;
    
    /**
     * 关键词类型
     */
    private int type = CommonConstant.TEMPLATE_KEYWORD_TYPE;

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getCellValue() {
        return cellValue;
    }

    public void setCellValue(String cellValue) {
        this.cellValue = cellValue;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
