/**
 *
 */
package com.facishare.open.manage.model;

import java.util.Comparator;

/**
 * 按pid升序排序
 *
 * @author songk
 * @date 2015年9月11日
 */
public class TreeVOByPIdComparator implements Comparator<TreeVO> {

    @Override
    public int compare(TreeVO o1, TreeVO o2) {

        return Integer.parseInt(o1.getpId()) - Integer.parseInt(o2.getpId());
    }

}
