package com.facishare.open.app.center.adapter.model.proto;

import io.protostuff.Tag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by xialf on 7/11/16.
 *
 * @author xialf
 */
@Getter
@Setter
@ToString
public abstract class ProtobufResult {
    public static final int OK_CODE = 200;
    public static final String OK_MSG = "OK";

    @Tag(1)
    private Integer code;

    @Tag(2)
    private String msg;

    public ProtobufResult() {
    }

    public ProtobufResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
