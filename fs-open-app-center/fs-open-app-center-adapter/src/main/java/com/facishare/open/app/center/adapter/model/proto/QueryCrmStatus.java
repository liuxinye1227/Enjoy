package com.facishare.open.app.center.adapter.model.proto;

import io.protostuff.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by xialf on 2017/03/09.
 *
 * @author xialf
 * @since 2017/03/09 4:01 PM
 */
public class QueryCrmStatus {
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
        private Integer status;

        public Result() {
        }

        public Result(Integer status) {
            super(OK_CODE, OK_MSG);
            this.status = status;
        }

        public Result(Integer code, String msg) {
            super(code, msg);
        }
    }
}
