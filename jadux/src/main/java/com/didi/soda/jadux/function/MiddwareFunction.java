package com.didi.soda.jadux.function;

public interface MiddwareFunction {
    MiddlewareInnerFunction apply(DispatchFunction dispatch, StateFunction getState);
}
