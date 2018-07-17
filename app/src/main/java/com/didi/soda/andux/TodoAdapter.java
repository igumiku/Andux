package com.didi.soda.andux;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.didi.soda.andux.model.Todo;

import java.util.List;

import io.reactivex.functions.Consumer;


class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.NoteViewHolder> {
    private List<Todo> notes;
    private final Consumer<Integer> onClickListener;

    public TodoAdapter(List<Todo> notes, Consumer<Integer> onClickListener) {
        this.notes = notes;
        this.onClickListener = onClickListener;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        final Todo todo = notes.get(position);
        holder.content.setText(todo.getText());
        holder.content.setChecked(todo.isCompleted());
        holder.itemView.setOnClickListener(view -> {
            try {
                onClickListener.accept(position);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setNotes(List<Todo> notes) {
        List<Todo> oldNotes = this.notes;
        this.notes = notes;
        notifyDataSetChanged();
//       DiffUtil.calculateDiff(new NotesDiffCallback(oldNotes, notes), false).dispatchUpdatesTo(this);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        public CheckBox content;

        public NoteViewHolder(View itemView) {
            super(itemView);
            content = (CheckBox) itemView;
        }
    }

}
