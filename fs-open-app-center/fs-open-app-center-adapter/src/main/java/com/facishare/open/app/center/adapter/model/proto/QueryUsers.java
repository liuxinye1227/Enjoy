package com.facishare.open.app.center.adapter.model.proto;

import io.protostuff.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by xialf on 2017/03/01.
 *
 * @author xialf
 * @since 2017/03/01 11:40 AM
 */
public class QueryUsers {
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
        private String fsEa;

        @Tag(4)
        private List<Integer> userIds;

        public Result() {
        }

        public Result(Integer code, String msg) {
            super(code, msg);
        }
    }
}
