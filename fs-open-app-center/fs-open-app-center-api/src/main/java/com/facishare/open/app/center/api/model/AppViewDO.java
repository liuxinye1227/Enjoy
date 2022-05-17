package com.facishare.open.app.center.api.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 应用的可见范围参数
 *
 * @author zenglb
 * @date 2015年8月17日
 */
public class AppViewDO implements Serializable {

    private static final long serialVersionUID = -3411096502131517143L;

    /**
     * 部门
     */
    private Integer[] department;

    /**
     * 员工
     */
    private Integer[] member;

    public Integer[] getDepartment() {
        return department;
    }

    public void setDepartment(Integer[] department) {
        this.department = department;
    }

    public Integer[] getMember() {
        return member;
    }

    public void setMember(Integer[] member) {
        this.member = member;
    }

    public String getJsonString() {
        return "{\"department\":"+ Arrays.toString(department).replaceAll("\\s*", "")+",\"member\":"+Arrays.toString(member).replaceAll("\\s*", "")+"}";
    }

    @Override
    public String toString() {
        return "AppCanViewVO [department=" + Arrays.toString(department) + ", member=" + Arrays.toString(member) + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(department);
        result = prime * result + Arrays.hashCode(member);
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
        AppViewDO other = (AppViewDO) obj;
        if (!Arrays.equals(department, other.department))
            return false;
        if (!Arrays.equals(member, other.member))
            return false;
        return true;
    }
}
