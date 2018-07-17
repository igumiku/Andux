package com.didi.soda.andux.store;

import com.didi.soda.andux.model.AppModel;
import com.didi.soda.jadux.Reducer;
import com.didi.soda.jadux.Store;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainStore extends Store<AppModel> {
    public MainStore(AppModel appModel, Reducer<AppModel> reducer) {
        super(appModel, reducer);
    }

    @Override
    public Disposable subscribe(Consumer<AppModel> consumer) {
        return publishSubject.subscribe(consumer);
    }
//   Observable<AppModel>
}
