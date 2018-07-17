package com.didi.soda.andux.reducer;

import com.didi.soda.andux.model.AppModel;
import com.didi.soda.andux.model.Todo;
import com.didi.soda.andux.model.VisivilityFilter;
import com.didi.soda.jadux.Action;
import com.didi.soda.jadux.Reducer;

import java.util.List;

/**
 * Created by lijiang on 17/07/2018.
 */

public class AppReducer implements Reducer<AppModel> {

    private AddTodoReducer addTodoReducer;
    private VisivilityFilterReducer visivilityFilterReducer;

    public AppReducer(AddTodoReducer addTodoReducer, VisivilityFilterReducer visivilityFilterReducer) {
        this.addTodoReducer = addTodoReducer;
        this.visivilityFilterReducer = visivilityFilterReducer;
    }

    @Override
    public AppModel reduce(AppModel appModel, Action action) {
        List<Todo> todos = appModel.getTodos();
        VisivilityFilter filter = appModel.getVisivilityFilter();

        appModel.setTodos(addTodoReducer.reduce(todos, action));
        appModel.setVisivilityFilter(visivilityFilterReducer.reduce(filter, action));
        return appModel;
    }
}
