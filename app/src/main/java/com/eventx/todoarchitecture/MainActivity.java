package com.eventx.todoarchitecture;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.eventx.todoarchitecture.data.TodoModel;
import com.eventx.todoarchitecture.viewmodels.TodoListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private RecyclerView mNotesRv;
    private TodoListViewModel viewModel;
    private TodosAdapter mTodosAdapter;
    private FloatingActionButton mAddFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddTodoActivity.class));
            }
        });
    }

    private void initialize() {
        mNotesRv = findViewById(R.id.recycler_view);
        viewModel = ViewModelProviders.of(this).get(TodoListViewModel.class);
        mTodosAdapter = new TodosAdapter(this, new ArrayList<TodoModel>(), this);
        mAddFab = findViewById(R.id.add_fab);
        mNotesRv.setLayoutManager(new LinearLayoutManager(this));
        mNotesRv.setAdapter(mTodosAdapter);
        viewModel.getNoteList().observe(this, new Observer<List<TodoModel>>() {
            @Override
            public void onChanged(@Nullable List<TodoModel> todoModels) {
                mTodosAdapter.addNote(todoModels);
            }
        });


    }

    @Override
    public boolean onLongClick(View view) {
        TodoModel todoModel = (TodoModel) view.getTag();
        viewModel.deleteNote(todoModel);
        Toast.makeText(this, todoModel.getTitle() + " deleted", Toast.LENGTH_SHORT).show();
        return true;
    }
}
