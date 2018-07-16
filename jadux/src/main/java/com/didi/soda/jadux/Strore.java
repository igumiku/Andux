package com.didi.soda.jadux;

import io.reactivex.Observable;

public class Strore<T> {

  private State<T> state;
  private Reducer reducer;

  protected void createStore() {

  }

  protected Observable<State> dispatch(Action action) {
    return Observable.just(action).map(it -> reducer.reduce(state, it)).doOnNext(it -> update(it));
  }

  private void update(State state) {
    this.state = state;
  }

  public State getState() {
    return this.state;
  }

}
