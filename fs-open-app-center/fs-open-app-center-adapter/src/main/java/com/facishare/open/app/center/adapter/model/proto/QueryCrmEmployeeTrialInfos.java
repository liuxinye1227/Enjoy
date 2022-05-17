package com.facishare.open.app.center.adapter.model.proto;

import com.facishare.open.app.pay.api.model.EmployeeTrialVo;
import io.protostuff.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

/**
 * Created by xialf on 2017/04/17.
 *
 * @author xialf
 * @since 2017/04/17 5:55 PM
 */
public abstract class QueryCrmEmployeeTrialInfos {
    @Getter
    @Setter
    @ToString
    public static class Arg {
        @Tag(1)
        private String fsEa;
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    public static class Result extends ProtobufResult {
        @Tag(3)
        private List<CrmEmployeeTrialItem> employeeTrialItems;

        public Result() {
        }

        public Result(Integer code, String msg) {
            super(code, msg);
        }

        public Result(List<CrmEmployeeTrialItem> employeeTrialItems) {
            super(ProtobufResult.OK_CODE, ProtobufResult.OK_MSG);
            this.employeeTrialItems = employeeTrialItems;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class CrmEmployeeTrialItem {
        private static final BeanCopier BEAN_COPIER = BeanCopier.create(EmployeeTrialVo.class, CrmEmployeeTrialItem.class, false);
        @Tag(1)
        private String fsEa;

        @Tag(2)
        private int userId;

        @Tag(3)
        private String appId;

        @Tag(4)
        private long gmtBegin;

        @Tag(5)
        private long gmtEnd;

        public static CrmEmployeeTrialItem from(EmployeeTrialVo vo) {
            final CrmEmployeeTrialItem item = new CrmEmployeeTrialItem();
            BEAN_COPIER.copy(vo, item, null);
            item.setUserId(vo.getUserId());
            item.setGmtBegin(vo.getTrialBeginDate().getTime());
            item.setGmtEnd(vo.getTrialExpirationDate().getTime());
            return item;
        }
    }
}
