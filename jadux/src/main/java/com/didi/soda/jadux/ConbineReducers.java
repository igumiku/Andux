package com.didi.soda.jadux;

import java.util.List;

public interface ConbineReducers<State> {
    Reducer<State> conbineReducers(List<Reducer> reducers);
}
