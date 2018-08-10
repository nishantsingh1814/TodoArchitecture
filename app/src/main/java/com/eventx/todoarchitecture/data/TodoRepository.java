package com.eventx.todoarchitecture.data;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import java.util.List;

public class TodoRepository {
    private final TodoModelDao todoModelDao;

    public TodoRepository(TodoDatabase todoDatabase) {
        this.todoModelDao = todoDatabase.todoItemAndTodosDao();
    }

    public LiveData<List<TodoModel>> getAllTodos() {
        return new LivePagedListBuilder(todoModelDao.getAllNotes(), new PagedList.Config.Builder()
                .setPageSize(10)
                .build()
        ).build();
    }

    public TodoModel getTodo(long id) {
        return todoModelDao.getTodoById(id);
    }

    public void insertTodo(TodoModel todoModel) {
        todoModelDao.insertNote(todoModel);
    }

    public void deleteTodo(TodoModel todoModel) {
        todoModelDao.deleteNote(todoModel);
    }

}
