package com.facishare.open.app.center.api.model.vo.custom.create.template;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zenglb on 2016/3/30.
 */
public class AutoReplyJsonVO  implements Serializable{
    /**
     * 当前回复消息的状态，合法值： 0-文本，1-图文
     */
    private int activeReplyType;
    /**
     * 自动回复中的文本回复
     */
    private String contentTxt;
    /**
     * 素材库中的图文素材ID
     */
    private String contentImgTxtID;
    /**
     * 规则名
     */
    private String ruleName;

    /**
     * 关键词+包含关系 json字符串
     */
    List<AutoReplyKeywordTypeJsonVO> keywordList;

    public int getActiveReplyType() {
        return activeReplyType;
    }

    public void setActiveReplyType(int activeReplyType) {
        this.activeReplyType = activeReplyType;
    }

    public String getContentTxt() {
        return contentTxt;
    }

    public void setContentTxt(String contentTxt) {
        this.contentTxt = contentTxt;
    }

    public String getContentImgTxtID() {
        return contentImgTxtID;
    }

    public void setContentImgTxtID(String contentImgTxtID) {
        this.contentImgTxtID = contentImgTxtID;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public List<AutoReplyKeywordTypeJsonVO> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<AutoReplyKeywordTypeJsonVO> keywordList) {
        this.keywordList = keywordList;
    }
}
