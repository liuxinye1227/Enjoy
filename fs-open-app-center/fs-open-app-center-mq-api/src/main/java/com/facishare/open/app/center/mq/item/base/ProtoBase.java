package com.facishare.open.app.center.mq.item.base;

/**
 * Created by liuq on 2016/2/18.
 */

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.Serializable;

public class ProtoBase implements CanProto, Serializable {

    @Override
    public byte[] toProto() {
        Schema schema = RuntimeSchema.getSchema(getClass());
        return ProtobufIOUtil.toByteArray(this, schema, LinkedBuffer.allocate(256));
    }

    @Override
    public void fromProto(byte[] bytes) {
        Schema schema = RuntimeSchema.getSchema(getClass());
        ProtobufIOUtil.mergeFrom(bytes, this, schema);
    }

}