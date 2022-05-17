package com.facishare.open.manage.dao;

import com.facishare.open.manage.model.AppIconDO;
import com.github.mybatis.mapper.ICrudMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by albeter on 17/3/9.
 */
public interface AppIconDAO extends ICrudMapper<AppIconDO> {

    @Select("SELECT * FROM app_icon")
    public List<AppIconDO> queryAllAppIcons();


    @Select("<script>"
            + "SELECT * FROM app_icon WHERE app_id IN "
            + "<foreach item='item' index='index' collection='appIds' open='(' separator=',' close=')'>"
            + "#{item}"
            + "</foreach>"
            + "</script>")
    public List<AppIconDO> queryByAppIds(@Param("appIds") List<String> appIds);

}
