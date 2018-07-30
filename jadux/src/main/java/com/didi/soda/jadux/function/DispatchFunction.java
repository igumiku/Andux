package com.didi.soda.jadux.function;

import com.didi.soda.jadux.AbsAction;
import io.reactivex.disposables.Disposable;

public interface DispatchFunction {
    Disposable apply(AbsAction action);
}
