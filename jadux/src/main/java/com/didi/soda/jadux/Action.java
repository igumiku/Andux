package com.didi.soda.jadux;

import com.didi.soda.jadux.utils.ActionTypes;

public class Action<T> {

    private String type = ActionTypes.UNKNOW;

    private T payload;

    public Action(String type, T payload) {
        this.type = type;
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    public String getType() {
        return type;
    }
}
