package com.didi.soda.jadux;

public abstract class Action<T> {

  private T payload;

  public Action(T payload) {
    this.payload = payload;
  }

  public T getPayload() {
    return payload;
  }
}
