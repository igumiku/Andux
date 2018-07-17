package com.didi.soda.andux.reducer;

import com.didi.soda.andux.action.AddTodoAction;
import com.didi.soda.andux.action.ToggleTodoAction;
import com.didi.soda.andux.model.Todo;
import com.didi.soda.andux.store.TodoState;
import com.didi.soda.jadux.Action;
import com.didi.soda.jadux.Reducer;

import java.util.List;

/**
 * Created by lijiang on 17/07/2018.
 */

public class AddTodoReducer implements Reducer<List<Todo>>{

    @Override
    public List<Todo> reduce(List<Todo> todos, Action action) {
         if(action instanceof AddTodoAction){
             todos.add(new Todo(((AddTodoAction) action).getPayload(),false));
         }else if (action instanceof ToggleTodoAction){
             Todo todo=todos.get(((ToggleTodoAction) action).getPayload());
             boolean completed=todo.isCompleted();
             todo.setCompleted(!completed);
         }

        return todos;
    }
}
