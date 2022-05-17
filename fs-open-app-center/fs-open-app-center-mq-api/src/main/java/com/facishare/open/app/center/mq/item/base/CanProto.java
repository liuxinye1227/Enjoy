package com.facishare.open.app.center.mq.item.base;

/**
 * Created by liuq on 2016/2/18.
 */
public interface CanProto {

    byte[] toProto();

    void fromProto(byte[] bytes);

}
