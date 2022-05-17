package com.facishare.open.app.center.adapter.model.proto;

import io.protostuff.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by xialf on 2017/03/15.
 *
 * @author xialf
 * @since 2017/03/15 4:21 PM
 */
public class IsAppAdmin {
    @Getter
    @Setter
    @ToString
    public static class Arg {
        @Tag(1)
        private String fsEa;

        @Tag(2)
        private Integer userId;

        @Tag(3)
        private String appId;
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    public static class Result extends ProtobufResult {
        @Tag(3)
        private Boolean isAppAdmin;

        public Result() {
        }

        public Result(Integer code, String msg) {
            super(code, msg);
        }

        public Result(Boolean isAppAdmin) {
            super(ProtobufResult.OK_CODE, OK_MSG);
            this.isAppAdmin = isAppAdmin;

        }
    }
}
