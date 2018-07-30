package com.didi.soda.jadux.middleware;

import com.didi.soda.jadux.AbsAction;
import com.didi.soda.jadux.Action;
import com.didi.soda.jadux.ActionFunction;
import com.didi.soda.jadux.function.DispatchFunction;
import com.didi.soda.jadux.function.MiddlewareInnerFunction;
import com.didi.soda.jadux.function.MiddwareFunction;
import com.didi.soda.jadux.function.StateFunction;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class ThunkMiddleware {
    public static MiddwareFunction createThunkMiddleware() {
        return new MiddwareFunction() {
            @Override
            public MiddlewareInnerFunction apply(DispatchFunction dispatch, StateFunction state) {
                return new MiddlewareInnerFunction() {
                    @Override
                    public DispatchFunction apply(DispatchFunction next) {
                        return new DispatchFunction() {
                            @Override
                            public Disposable apply(AbsAction action) {
                                if (action instanceof Action) {
                                    return next.apply(action);
                                } else if (action instanceof ActionFunction) {
                                    ((ActionFunction) action).apply(dispatch, state);
                                    return null;
                                } else {
                                    throw new IllegalArgumentException("Action type should be Action or ActionFunction!");
                                }
                            }
                        };
                    }
                };
            }
        };
    }
}
