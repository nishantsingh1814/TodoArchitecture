package com.eventx.todoarchitecture.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TodoModelDao {

    @Query("SELECT * FROM TodoModel")
    LiveData<List<TodoModel>> getAllNotes();

    @Query("SELECT * FROM TodoModel WHERE id=:id")
    TodoModel getTodoById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(TodoModel todoModel);


    @Delete
    void deleteNote(TodoModel todoModel);
}
