package com.didi.soda.jadux;

import android.security.keystore.StrongBoxUnavailableException;

import com.didi.soda.jadux.collection.JxList;
import com.didi.soda.jadux.function.JxFunction0;
import com.didi.soda.jadux.middleware.ThunkMiddleware;

import java.util.HashMap;
import java.util.List;

public class Jadux {
    public static void createStore(){

    }

    public static <State> Reducer combineReducers(HashMap<String,Reducer> reducers){
       return new Reducer<State>() {
           @Override
           public State reduce(State state, Action action) {

               return null;
           }
       };
    }

    public static void bindActionCreators(){

    }



    public static void compose(){
    }
}
