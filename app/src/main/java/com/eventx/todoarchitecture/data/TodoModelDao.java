package com.eventx.todoarchitecture.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NoteModelDao {

    @Query("SELECT * FROM NoteModel")
    LiveData<List<NoteModel>> getAllNotes();

    @Query("SELECT * FROM NoteModel WHERE id=:id")
    NoteModel getNoteById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteModel noteModel);


    @Delete
    void deleteNote(NoteModel noteModel);
}
