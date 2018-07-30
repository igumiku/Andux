package com.didi.soda.jadux;

import com.didi.soda.jadux.function.JxFunction0;


public interface Middleware {
    public Middleware applyStore(Store store);

    public Middleware applyNextMiddleWare(Middleware next);

    public Action applyBeforeDispatch(Action action);


    public Middleware applyAfterDispatch(Action action);

    public Middleware applyDispatch(Action action);
}
