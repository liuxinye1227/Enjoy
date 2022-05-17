/**
 *
 */
package com.facishare.open.manage.utils;

import com.facishare.open.manage.model.MenuPageVO;
import com.facishare.open.manage.model.TreeVO;
import com.facishare.open.msg.model.CustomMenuVO;
import com.facishare.open.msg.model.MenuVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单对象转换   MenuPageVO对象转换为  CustomMenuVO
 *
 * @author songk
 * @date 2015年9月11日
 */
public class MenuFormatter {

    public static CustomMenuVO getJsonStr(MenuPageVO menuPageVO, String appId, String name) {

        List<String> pIdList = new ArrayList<String>();

        List<TreeVO> treeVOList = menuPageVO.getTreeVOList();

        if (null != treeVOList && treeVOList.size() > 0) {

            for (TreeVO treeVO : treeVOList) {

                if (treeVO.getpId().equals("0")) {

                    pIdList.add(treeVO.getId());
                }
            }
        }

        CustomMenuVO customMenuVO = new CustomMenuVO();

        customMenuVO.setAppId(appId);
        customMenuVO.setName(name);

        List<MenuVO> menuVOList = null;
        if (pIdList.size() > 0) {

            menuVOList = new ArrayList<MenuVO>();

            for (String pId : pIdList) {

                MenuVO menuVO = new MenuVO();

                List<MenuVO> subMenuVOList = new ArrayList<MenuVO>();

                for (TreeVO treeVO : treeVOList) {

                    if (pId.equals(treeVO.getId())) {
                        menuVO.setName(treeVO.getName());
                        menuVO.setType(treeVO.getType());
                        menuVO.setActionParam(treeVO.getActionParam());

                    } else {
                        if (pId.equals(treeVO.getpId())) {
                            MenuVO subMenuVO = new MenuVO();

                            subMenuVO.setName(treeVO.getName());
                            subMenuVO.setType(treeVO.getType());
                            subMenuVO.setActionParam(treeVO.getActionParam());
                            subMenuVOList.add(subMenuVO);
                        }
                    }
                }

                menuVO.setSubMenuList(subMenuVOList);
                menuVOList.add(menuVO);
            }
        }
        if (null != menuVOList) {

            customMenuVO.setMenuList(menuVOList);
        }

        return customMenuVO;
    }
}