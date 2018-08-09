package com.eventx.todoarchitecture.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

public class TodoRepository {
    private final TodoModelDao todoModelDao;

    public TodoRepository(TodoDatabase todoDatabase) {
        this.todoModelDao = todoDatabase.todoItemAndTodosDao();
    }

    public LiveData<List<TodoModel>> getAllTodos() {
        return todoModelDao.getAllNotes();
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
