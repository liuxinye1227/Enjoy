package com.facishare.pay.business.dao.base;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import com.facishare.open.common.storage.mysql.dao.BaseDao;

/**
 * 切换对应数据源
 * @author guom
 * @date 2015/10/13
 */
public class BusinessBaseDAO<T extends Serializable> extends BaseDao<T> {
    
    @Resource(name="business_sqlSessionFactory")
    public void setSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionTemplate(new SqlSessionTemplate(sqlSessionFactory));
    }
}
