package com.facishare.open.app.center.api.model;

import com.facishare.open.app.center.api.constants.CommonConstant;
import com.facishare.open.app.center.api.utils.JsonKit;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by xialf on 10/28/15.
 * 可见范围, 建议代替AppViewDO:这里使用了List代替数组,便于与其它服务交互
 *
 * @author xialf
 */
public final class EmployeeRange implements Serializable {
    private static final long serialVersionUID = 1991312131628844180L;
    private List<Integer> member = null;
    private List<Integer> department = null;

    public List<Integer> getMember() {
        if (member == null) {
            member = new ArrayList<>(1);
        }
        return member;
    }

    public void setMember(List<Integer> member) {
        this.member = member;
        if (this.member == null) {
            this.member = new ArrayList<>(1);
        }
    }

    public List<Integer> getDepartment() {
        if (department == null) {
            department = new ArrayList<>(1);
        }
        return department;
    }

    public void setDepartment(List<Integer> department) {
        this.department = department;
        if (this.department == null) {
            this.department = new ArrayList<>(1);
        }
    }

    /**
     * 转变为等价的AppViewDO.
     *
     * @return 等价的AppViewDO
     */
    public AppViewDO toAppView() {
        AppViewDO appViewDO = new AppViewDO();
        appViewDO.setDepartment(this.getDepartment().toArray(new Integer[this.getDepartment().size()]));
        appViewDO.setMember(this.getMember().toArray(new Integer[this.getMember().size()]));
        return appViewDO;
    }

    /**
     * 合并员工集范围.
     *
     * @param employeeRange 带合并的员工范围
     * @return this
     */
    public EmployeeRange unionWith(final EmployeeRange employeeRange) {
        Set<Integer> members = new HashSet<>(this.getMember());
        members.addAll(employeeRange.getMember());
        this.member = new ArrayList<>(members);

        Set<Integer> departments = new HashSet<>(this.getDepartment());
        departments.addAll(employeeRange.getDepartment());
        this.department = new ArrayList<>(departments);
        return this;
    }

    /**
     * 是否包含全公司.
     *
     * @return .
     */
    public boolean includeWholeCompany() {
        return this.getDepartment().contains(CommonConstant.COMPANY_DEPARTMENT_CODE);
    }

    /**
     * 是否为空.(不包含任何员工,也不包含任何部门)
     * @return
     */
    public boolean isEmpty() {
        return CollectionUtils.isEmpty(this.department) && CollectionUtils.isEmpty(this.member);
    }

    /**
     * 等价转化, null->null.
     *
     * @param appViewDO 可见范围
     * @return 等价的EmployeeRange
     */
    public static EmployeeRange fromAppView(final AppViewDO appViewDO) {
        EmployeeRange employeeRange = new EmployeeRange();
        if (null != appViewDO.getDepartment() && appViewDO.getDepartment().length > 0) {
            employeeRange.setDepartment(Lists.newArrayList(appViewDO.getDepartment()));
        }

        if (null != appViewDO.getMember() && appViewDO.getMember().length > 0) {
            employeeRange.setMember(Lists.newArrayList(appViewDO.getMember()));
        }

        return employeeRange;
    }

    @Override
    public String toString() {
        return "EmployeeRange{" +
                "member=" + JsonKit.object2json(member) +
                ", department=" + JsonKit.object2json(department) +
                '}';
    }
}
