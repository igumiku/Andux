package com.didi.soda.jadux;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public abstract class Store<State> {

    private State state;
    private Reducer<State> reducer;

    protected PublishSubject<State> publishSubject = PublishSubject.create();

    public Store(State state, Reducer<State> reducer) {
        this.state = state;
        this.reducer = reducer;
    }

    protected void createStore() {

    }

    public Disposable dispatch(Action action) {
        return Observable.just(action).map(it -> reducer.reduce(state, it)).subscribe(it -> {
            update(it);
        });
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
