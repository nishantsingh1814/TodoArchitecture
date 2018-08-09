package com.eventx.todoarchitecture.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = TodoModel.class, version = 1)
public abstract class TodoDatabase extends RoomDatabase {

    private static TodoDatabase INSTANCE;

    public static TodoDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TodoDatabase.class, "notes.db").build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE=null;
    }
    public abstract TodoModelDao todoItemAndTodosDao();
}
