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
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.eventx.todoarchitecture.data.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener {

    private RecyclerView mNotesRv;
    private NoteListViewModel viewModel;
    private NotesAdapter mNotesAdapter;
    private FloatingActionButton mAddFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        mAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
            }
        });
    }

    private void initialize() {
        mNotesRv = findViewById(R.id.recycler_view);
        viewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);
        mNotesAdapter = new NotesAdapter(this, new ArrayList<NoteModel>(), this);
        mAddFab = findViewById(R.id.add_fab);
        mNotesRv.setLayoutManager(new LinearLayoutManager(this));
        mNotesRv.setAdapter(mNotesAdapter);
        viewModel.getNoteList().observe(this, new Observer<List<NoteModel>>() {
            @Override
            public void onChanged(@Nullable List<NoteModel> noteModels) {
                mNotesAdapter.addNote(noteModels);
            }
        });


    }

    @Override
    public boolean onLongClick(View view) {
        NoteModel noteModel = (NoteModel) view.getTag();
        viewModel.deleteNote(noteModel);
        Toast.makeText(this, noteModel.getTitle() + " deleted", Toast.LENGTH_SHORT).show();
        return true;
    }
}
