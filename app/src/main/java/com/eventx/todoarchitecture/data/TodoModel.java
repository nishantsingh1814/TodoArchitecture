package com.eventx.todoarchitecture.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class TodoModel {

    @PrimaryKey(autoGenerate = true)
    public long id;
    private String title;
    private String description;
    private long date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TodoModel(String title, String description, long date, String repeat) {

        this.title = title;
        this.description = description;
        this.date = date;
        this.repeat = repeat;
    }

    private String repeat;

}
