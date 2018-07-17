package com.didi.soda.andux.model;

/**
 * Created by lijiang on 17/07/2018.
 */

public class Todo {
    public Todo(String text, boolean completed) {
        this.text = text;
        this.completed = completed;
    }

    private String text;

    private boolean completed;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
