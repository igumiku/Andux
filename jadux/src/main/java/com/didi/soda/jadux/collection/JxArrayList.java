package com.didi.soda.jadux.collection;

import com.didi.soda.jadux.function.JxFunction0;

import java.util.ArrayList;
import java.util.Iterator;

import io.reactivex.functions.BiFunction;

public class JxArrayList<E> extends ArrayList<E> implements JxList<E> {

    @Override
    public <R> JxList<R> map(JxFunction0<E, R> mapper) {
        JxList<R> res = new JxArrayList<>();
        for (E e : this) {
            R r = mapper.invoke(e);
            res.add(r);
        }
        return res;
    }

    @Override
    public JxList<E> filter(JxFunction0<E, Boolean> filter) {
        JxList<E> res = this.copy();
        for (Iterator<E> it = res.iterator(); it.hasNext(); ) {
            E e = it.next();
            if (!filter.invoke(e)) {
                it.remove();
            }
        }
        return res;
    }

    @Override
    public JxList<E> copy() {
        JxList<E> copy = new JxArrayList<>();
        copy.addAll(this);
        return copy;
    }

    @Override
    public JxList<E> forEach(JxFunction0<E, E> function) {
        for (E e : this) {
            function.invoke(e);
        }
        return this;
    }

    @Override
    public E reduce(BiFunction<E, E, E> reducer) throws Exception {
        int size = this.size();
        if (size == 0) {
            throw new IllegalArgumentException("List can't not be null or empty!");
        }
        E res = this.get(0);
        for (int i = 1; i < size - 1; i++) {
            E e = this.get(i);

            res = reducer.apply(res, e);
        }

        return res;
    }
}
