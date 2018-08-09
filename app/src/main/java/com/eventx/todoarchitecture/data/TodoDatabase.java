package com.eventx.todoarchitecture.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = NoteModel.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase INSTANCE;

    public static NoteDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "notes.db").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE=null;
    }
    public abstract NoteModelDao noteItemAndNotesModel();
}
