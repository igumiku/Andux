package com.didi.soda.andux.reducer;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.didi.soda.andux.action.Actions;
import com.didi.soda.andux.model.AppModel;
import com.didi.soda.andux.model.Todo;
import com.didi.soda.andux.model.VisivilityFilter;
import com.didi.soda.jadux.Action;

import java.util.List;
import java.util.function.BiFunction;


@RequiresApi(api = Build.VERSION_CODES.N)
public class Reducers {

    public static BiFunction<List<Todo>, Action, List<Todo>> reduceTodos = (todos, action) -> {
        if (Actions.ADD_TODO.equals(action.getType())) {
            todos.add(new Todo((String) action.getPayload(), false));
        } else if (Actions.TOGGLE_TODO.equals(action.getType())) {
            Todo todo = todos.get((Integer) action.getPayload());
            boolean completed = todo.isCompleted();
            todo.setCompleted(!completed);
        }
        return todos;
    };

    public static BiFunction<VisivilityFilter, Action, VisivilityFilter> reduceVisivility = (visible, action) -> {
        if (Actions.TOGGLE_TODO.equals(action.getType())) {
            return ((VisivilityFilter) action.getPayload());
        }
        return visible;
    };

    public static BiFunction<AppModel, Action, AppModel> reduceApp = (appModel, action) -> {
        List<Todo> todos = appModel.getTodos();
        VisivilityFilter filter = appModel.getVisivilityFilter();

        appModel.setTodos(reduceTodos.apply(todos, action));
        appModel.setVisivilityFilter(reduceVisivility.apply(filter, action));
        return appModel;
    };
}
