package com.didi.soda.jadux.middleware;

import android.util.Log;

import com.didi.soda.jadux.AbsAction;
import com.didi.soda.jadux.Action;
import com.didi.soda.jadux.ActionFunction;
import com.didi.soda.jadux.Middleware;
import com.didi.soda.jadux.Store;
import com.didi.soda.jadux.function.DispatchFunction;
import com.didi.soda.jadux.function.JxFunction0;
import com.didi.soda.jadux.function.JxFunction1;
import com.didi.soda.jadux.function.MiddlewareInnerFunction;
import com.didi.soda.jadux.function.MiddwareFunction;
import com.didi.soda.jadux.function.StateFunction;

import io.reactivex.disposables.Disposable;

public class LogMiddleWare {

    public static MiddwareFunction createLogMiddleware() {
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
                                    Log.d("Dispatching", ((Action) action).getType());
                                    Disposable disposable = next.apply(action);
                                    Log.d("After dispatched", state.getState().toString());
                                    return disposable;
                                } else if (action instanceof ActionFunction) {
                                    throw new IllegalArgumentException("Please include ThunkMiddleware to deal with ActionFunction!");
                                } else {
                                    throw new IllegalArgumentException("Action should be Action or ActionFunction!");
                                }
                            }
                        };
                    }
                };
            }
        };
    }
}
