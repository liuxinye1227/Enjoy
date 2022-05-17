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
 * @since 2017/03/01 11:41 AM
 */
public class RemoveUsers {
    @Getter
    @Setter
    @ToString
    public static class Arg {
        @Tag(1)
        private String fsEa;

        @Tag(2)
        private List<Integer> userIds;
    }

    @Getter
    @Setter
    @ToString(callSuper = true)
    public static class Result extends ProtobufResult {
        public Result() {
        }

        public Result(Integer code, String msg) {
            super(code, msg);
        }
    }
}
