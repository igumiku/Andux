package com.didi.soda.jadux.function;

public interface MiddlewareInnerFunction {
    DispatchFunction apply(DispatchFunction source);
}
