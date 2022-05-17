package com.facishare.open.app.center.adapter.model.proto;

import io.protostuff.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by xialf on 2017/02/24.
 *
 * @author xialf
 * @since 2017/02/24 9:47 AM
 */
public class QueryCrmAppAdmins {
    @Getter
    @Setter
    @ToString
    public static class Arg {
        @Tag(1)
        private String fsEa;

        @Tag(2)
        private Integer operatorId;
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    public static class Result extends ProtobufResult {
        @Tag(3)
        private List<Integer> appAdmins;

        public Result() {
        }

        public Result(List<Integer> appAdmins) {
            super(ProtobufResult.OK_CODE, ProtobufResult.OK_MSG);
            this.appAdmins = appAdmins;
        }

        public Result(Integer code, String msg) {
            super(code, msg);
        }
    }
}
