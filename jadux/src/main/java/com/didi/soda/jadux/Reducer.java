package com.didi.soda.jadux;

public interface Reducer<State> {
   State reduce(State state,Action action);
}
