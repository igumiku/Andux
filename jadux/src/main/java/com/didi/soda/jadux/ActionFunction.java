package com.didi.soda.jadux;

import com.didi.soda.jadux.function.DispatchFunction;
import com.didi.soda.jadux.function.StateFunction;

public interface ActionFunction extends AbsAction {
    void apply(DispatchFunction dispatch, StateFunction state);
}
