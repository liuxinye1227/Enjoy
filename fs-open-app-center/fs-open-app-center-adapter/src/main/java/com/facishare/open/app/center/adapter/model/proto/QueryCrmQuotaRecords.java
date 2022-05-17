package com.facishare.open.app.center.adapter.model.proto;

import com.facishare.open.app.pay.api.model.QuotaRecordVo;
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
 * @since 2017/04/17 4:19 PM
 */
public abstract class QueryCrmQuotaRecords {
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
        private List<QuotaRecordItem> quotaRecordItems;

        public Result() {
        }

        public Result(Integer code, String msg) {
            super(code, msg);
        }

        public Result(List<QuotaRecordItem> quotaRecordItems) {
            super(ProtobufResult.OK_CODE, ProtobufResult.OK_MSG);
            this.quotaRecordItems = quotaRecordItems;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class QuotaRecordItem {
        private static final BeanCopier BEAN_COPIER = BeanCopier.create(QuotaRecordVo.class, QuotaRecordItem.class, false);

        @Tag(1)
        private Long id;

        @Tag(2)
        private String fsEa;

        @Tag(3)
        private String appId;

        @Tag(4)
        private int quota;

        @Tag(5)
        private int quotaType;

        @Tag(6)
        private long gmtBegin;

        @Tag(7)
        private long gmtEnd;

        @Tag(8)
        private long gmtCreate;

        public static QuotaRecordItem from(QuotaRecordVo vo) {
            final QuotaRecordItem item = new QuotaRecordItem();
            BEAN_COPIER.copy(vo, item, null);
            item.setQuotaType(vo.getQuotaType().getCode());
            item.setGmtBegin(vo.getGmtBegin().getTime());
            item.setGmtEnd(vo.getGmtEnd().getTime());
            item.setGmtCreate(vo.getGmtCreate().getTime());
            return item;
        }

    }
}
