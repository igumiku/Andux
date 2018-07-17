package com.didi.soda.andux.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lijiang on 17/07/2018.
 */

public class AppModel {
    private VisivilityFilter visivilityFilter = VisivilityFilter.SHOW_ALL;

    private List<Todo> todos = new ArrayList<>();

    public VisivilityFilter getVisivilityFilter() {
        return visivilityFilter;
    }

    public void setVisivilityFilter(VisivilityFilter visivilityFilter) {
        this.visivilityFilter = visivilityFilter;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
