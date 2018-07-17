package com.didi.soda.jadux;

import io.reactivex.Observable;

public class Strore<State> {

  private State state;
  private Reducer<State> reducer;

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
