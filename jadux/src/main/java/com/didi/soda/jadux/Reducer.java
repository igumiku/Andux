package com.didi.soda.jadux;

public interface Reducer {
   public State reduce(State state,Action action);
}
