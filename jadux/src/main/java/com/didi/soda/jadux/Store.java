package com.didi.soda.jadux;

import com.didi.soda.jadux.collection.JxArrayList;
import com.didi.soda.jadux.collection.JxList;
import com.didi.soda.jadux.function.DispatchFunction;
import com.didi.soda.jadux.function.MiddlewareInnerFunction;
import com.didi.soda.jadux.function.MiddwareFunction;
import com.didi.soda.jadux.middleware.LogMiddleWare;
import com.didi.soda.jadux.middleware.ThunkMiddleware;
import com.didi.soda.jadux.utils.ActionTypes;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public abstract class Store<State> {

    private State state;
    private Reducer<State> reducer;

    private boolean isDispathching = false;

    protected PublishSubject<State> publishSubject = PublishSubject.create();

    public Store createStore(Reducer<State> reducer, State preloadedState) {
        JxList<MiddwareFunction> middwares = new JxArrayList<>();

        //use default middlewares.
        middwares.add(ThunkMiddleware.createThunkMiddleware());
        middwares.add(LogMiddleWare.createLogMiddleware());
        createStore(reducer, preloadedState, middwares);

        return this;
    }

    private Store createStore(Reducer<State> reducer, State preloadedState, JxList<MiddwareFunction> middlewares) {
        if (reducer == null) {
            throw new IllegalArgumentException("reducer must not be null!");
        }

        this.reducer = reducer;
        this.state = preloadedState;
        applyMiddleware(middlewares);

        return this;
    }

    public DispatchFunction dispatchFunction = action -> Store.this.dispatch(action);

    public Disposable dispatch(AbsAction absAction) {
        if (absAction instanceof Action) {
            Action action = (Action) absAction;
            if (ActionTypes.UNKNOW.equals(action.getType())) {
                throw new IllegalArgumentException("Unknow action type!");
            }

            if (isDispathching) {
                throw new IllegalStateException("Reducers may not dispatch actions.");
            }

            if (reducer == null) {
                throw new IllegalStateException("Call createStore first to init the store!");
            }

            return Single.just(action).map(it -> reducer.reduce(state, it)).doOnSubscribe(it -> {
                isDispathching = true;
            }).doFinally(() -> {
                isDispathching = false;
            }).subscribe(it -> {
                update(it);
            });
        } else if (absAction instanceof ActionFunction) {
            throw new IllegalArgumentException("Please include ThunkMiddleware to deal with ActionFunction!");
        } else {
            throw new IllegalArgumentException("Action should be Action or ActionFunction!");
        }
    }

    private void applyMiddleware(JxList<MiddwareFunction> middlewares) {
        if (middlewares == null) {
            return;
        }

        JxList<MiddlewareInnerFunction> applyMiddlewares = middlewares.map(middleware -> middleware.apply(this::dispatch, this::getState));

        try {
            this.dispatchFunction = compose(applyMiddlewares).apply(this::dispatch);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private MiddlewareInnerFunction compose(JxList<MiddlewareInnerFunction> functions) throws Exception {
        return functions.reduce(ComposeMiddleware.compose);
    }

    private void update(State state) {
        this.state = state;
        publishSubject.onNext(state);
    }

    public abstract Disposable subscribe(io.reactivex.functions.Consumer<State> consumer);

    public State getState() {
        return this.state;
    }

}
