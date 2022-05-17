/**
 *
 */
package com.facishare.open.manage.model;

import java.util.Comparator;

/**
 * 按id升序排序
 *
 * @author songk
 * @date 2015年9月11日
 */
public class TreeVOByIdComparator implements Comparator<TreeVO> {

    @Override
    public int compare(TreeVO o1, TreeVO o2) {

        return Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId());
    }

}
