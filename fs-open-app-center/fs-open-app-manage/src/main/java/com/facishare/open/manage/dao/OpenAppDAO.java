package com.facishare.open.manage.dao;

import com.facishare.open.app.center.api.model.OpenAppDO;
import com.facishare.open.app.center.api.model.enums.PayTypeEnum;
import com.facishare.open.common.storage.mysql.dao.Pager;
import com.github.mybatis.mapper.ICrudMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 应用操作
 *
 * @author zenglb
 * @date 2015年8月3日
 */
public interface OpenAppDAO extends ICrudMapper<OpenAppDO> {

    long countService(@Param("startCreateTime") Date startCreateTime, @Param("endCreateTime") Date endCreateTime);

    long countServiceEa(@Param("startCreateTime") Date startCreateTime, @Param("endCreateTime") Date endCreateTime);

}