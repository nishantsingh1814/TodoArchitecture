package com.eventx.todoarchitecture;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.eventx.todoarchitecture.data.NoteDatabase;
import com.eventx.todoarchitecture.data.NoteModel;

import java.util.List;

public class NoteListViewModel extends AndroidViewModel {
    private final LiveData<List<NoteModel>> noteList;
    private NoteDatabase noteDatabase;


    public NoteListViewModel(Application application) {
        super(application);

        this.noteDatabase = NoteDatabase.getDatabase(this.getApplication());
        noteList = noteDatabase.noteItemAndNotesModel().getAllNotes();
    }

    public LiveData<List<NoteModel>> getNoteList() {
        return noteList;
    }

    public void deleteNote(NoteModel noteModel) {
        new DeleteAsyncTask(noteDatabase).execute(noteModel);
    }

    class DeleteAsyncTask extends AsyncTask<NoteModel, Void, Void> {
        private NoteDatabase noteDatabase;

        DeleteAsyncTask(NoteDatabase noteDatabase) {
            this.noteDatabase = noteDatabase;
        }


        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteDatabase.noteItemAndNotesModel().deleteNote(noteModels[0]);
            return null;
        }
    }
}
