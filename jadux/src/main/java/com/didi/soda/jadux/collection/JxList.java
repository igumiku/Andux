package com.didi.soda.jadux.collection;

import com.didi.soda.jadux.function.JxFunction0;

import java.util.List;

import io.reactivex.functions.BiFunction;

public interface JxList<T> extends List<T>{
    public <R> JxList<R> map(JxFunction0<T,R> mapper);

    JxList<T> filter(JxFunction0<T,Boolean> filter);

    JxList<T> copy();

    JxList<T> forEach(JxFunction0<T,T> function);

    T reduce(BiFunction<T,T,T> reducer) throws Exception;
}
