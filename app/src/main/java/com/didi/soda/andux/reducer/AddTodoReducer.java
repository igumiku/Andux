package com.didi.soda.andux.reducer;

import com.didi.soda.andux.action.Actions;
import com.didi.soda.andux.model.Todo;
import com.didi.soda.jadux.Action;
import com.didi.soda.jadux.Reducer;

import java.util.List;

/**
 * Created by lijiang on 17/07/2018.
 */

public class AddTodoReducer implements Reducer<List<Todo>> {

    @Override
    public List<Todo> reduce(List<Todo> todos, Action action) {
        if (Actions.ADD_TODO.equals(action.getType())) {
            todos.add(new Todo((String) action.getPayload(), false));
        } else if (Actions.TOGGLE_TODO.equals(action.getType())) {
            Todo todo = todos.get((Integer) action.getPayload());
            boolean completed = todo.isCompleted();
            todo.setCompleted(!completed);
        }
        return todos;
    }
}
