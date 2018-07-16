package com.didi.soda.jadux;

public interface Reducer<T> {
   public State reduce(State<T> state,Action action);
}
