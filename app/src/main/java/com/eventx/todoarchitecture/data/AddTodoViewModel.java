package com.eventx.todoarchitecture.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

public class AddNoteViewModel extends AndroidViewModel {
    private NoteDatabase noteDatabase;

    public AddNoteViewModel(@NonNull Application application) {
        super(application);

        noteDatabase = NoteDatabase.getDatabase(getApplication());
    }

    public void addNote(NoteModel noteModel) {
        new AddNoteTask(noteDatabase).execute(noteModel);
    }

    private class AddNoteTask extends AsyncTask<NoteModel, Void, Void> {
        private NoteDatabase noteDatabase;

        AddNoteTask(NoteDatabase noteDatabase) {
            this.noteDatabase = noteDatabase;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteDatabase.noteItemAndNotesModel().insertNote(noteModels[0]);
            return null;
        }
    }
}
