package com.didi.soda.andux;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import com.didi.soda.andux.model.AppModel;
import com.didi.soda.andux.reducer.AddTodoReducer;
import com.didi.soda.andux.reducer.AppReducer;
import com.didi.soda.andux.reducer.VisivilityFilterReducer;
import com.didi.soda.andux.store.MainStore;

public class AnduxApp extends Application {

    public MainStore store;

    @Override
    public void onCreate() {
        super.onCreate();

        final AppReducer vanillaReducer = new AppReducer(new AddTodoReducer(),new VisivilityFilterReducer());
        store = new MainStore(new AppModel(),vanillaReducer);
//        store = Store.create(
//                new SetStateReducer<>(
//                        new UndoableReducer<>(vanillaReducer)));

    }
}
