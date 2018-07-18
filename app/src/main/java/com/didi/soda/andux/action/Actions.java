package com.didi.soda.andux.action;

import com.didi.soda.andux.model.Todo;
import com.didi.soda.andux.model.VisivilityFilter;
import com.didi.soda.jadux.Action;

public class Actions {
    public static final String ADD_TODO = "main_add_todo";
    public static final String TOGGLE_TODO = "main_toggle_todo";
    public static final String CHANGE_VISIBLE_FILTER = "main_filter";

    public static Action addTodo(String text) {
        return new Action<Todo>(Actions.ADD_TODO, new Todo(text, false));
    }

    public static Action toggleTodo(int index) {
        return new Action<Integer>(Actions.TOGGLE_TODO, index);
    }

    public static Action setVisibleFilter(VisivilityFilter filter) {
        return new Action<VisivilityFilter>(Actions.CHANGE_VISIBLE_FILTER, filter);
    }
}
