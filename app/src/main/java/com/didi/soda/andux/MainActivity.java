package com.didi.soda.andux;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.didi.soda.andux.action.AddTodoAction;
import com.didi.soda.andux.action.ToggleTodoAction;
import com.didi.soda.andux.action.VisivilityAction;
import com.didi.soda.andux.model.Todo;
import com.didi.soda.andux.model.VisivilityFilter;
import com.didi.soda.andux.store.MainStore;
import com.didi.soda.jadux.Store;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private MainStore store;

    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        store = ((AnduxApp) getApplicationContext()).store;
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        setupSpinner(spinner, store);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final TodoAdapter adapter = new TodoAdapter(store.getState().getTodos(), position -> store.dispatch(new ToggleTodoAction(position)));

        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);

        disposable = store.subscribe(appModel -> {
            //filter
            List<Todo> res = new ArrayList<>();

            for (Todo todo : appModel.getTodos()) {
                if (appModel.getVisivilityFilter() == VisivilityFilter.SHOW_ACTIVE) {
                    if (!todo.isCompleted()) {
                        res.add(todo);
                    }
                } else if (appModel.getVisivilityFilter() == VisivilityFilter.SHOW_COMPLETED) {
                    if (todo.isCompleted()) {
                        res.add(todo);
                    }
                }
            }

            if (appModel.getVisivilityFilter() == VisivilityFilter.SHOW_ALL) {
                res = appModel.getTodos();
            }

            adapter.setNotes(res);
            spinner.setSelection(appModel.getVisivilityFilter().ordinal());
        });

        final EditText editText = (EditText) findViewById(R.id.note_edit_text);
        findViewById(R.id.add).setOnClickListener(view -> {
            String note = editText.getText().toString();
            store.dispatch(new AddTodoAction(note));
            editText.setText(null);
        });
    }

    private void setupSpinner(Spinner spinner, Store store) {
        SpinnerAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, VisivilityFilter.values());
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                VisivilityFilter filter = VisivilityFilter.values()[position];
                store.dispatch(new VisivilityAction(filter));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        menu.add(R.string.undo)
//                .setIcon(R.drawable.ic_undo_24dp)
//                .setOnMenuItemClickListener(menuItem -> {
//                    store.dispatch(UndoableReducer.pop());
//                    return true;
//                })
//                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
//        return true;
//    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }

}
