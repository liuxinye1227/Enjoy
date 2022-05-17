package com.facishare.open.app.center.adapter.model.proto;

import com.facishare.common.fsi.ProtoBase;
import io.protostuff.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by xialf on 2017/03/01.
 *
 * @author xialf
 * @since 2017/03/01 11:18 AM
 */
public class QueryCrmAvail {
    @Getter
    @Setter
    @ToString
    public static class Arg {
        /**
         * 企业账号.
         */
        @Tag(1)
        private String fsEa;

        /**
         * 用户id.
         */
        @Tag(2)
        private Integer userId;
    }

    @Getter
    @Setter
    @ToString
    public static class Result extends ProtoBase {
        @Tag(1)
        private Integer code;

        /**
         * 1可见,2不可见.
         */
        @Tag(2)
        private Integer availability;

        public Result() {
        }

        public Result(Integer code) {
            this.code = code;
        }
    }
}
