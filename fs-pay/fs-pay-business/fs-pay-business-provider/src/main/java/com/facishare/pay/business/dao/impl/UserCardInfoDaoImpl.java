package com.facishare.pay.business.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.facishare.pay.business.dao.UserCardInfoDAO;
import com.facishare.pay.business.dao.base.BusinessBaseDAO;
import com.facishare.pay.business.model.UserCardInfoDo;

@Service
public class UserCardInfoDaoImpl extends BusinessBaseDAO<UserCardInfoDo> implements UserCardInfoDAO {

    @Override
    public int addUserCardInfo(UserCardInfoDo userCardInfoDo) {
        return this.save("addUserCardInfo", userCardInfoDo);
    }

    @Override
    public List<UserCardInfoDo> queryUserCardInfo(final Long userInfoId,
            final String cardNo) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("userInfoId", userInfoId);
        params.put("cardNo", cardNo);
        return this.getList("queryUserCardInfo", params);
    }

    @Override
    public int unBoundUserCardInfo(Long userInfoId, String cardNo) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("userInfoId", userInfoId);
        params.put("cardNo", cardNo);
        return this.update("deleteUserCardInfo", params);
    }

    @Override
    public UserCardInfoDo queryUserCardInfoById(Long id) {
        return this.get("queryUserCardInfoById", id);
    }
    
    

}
