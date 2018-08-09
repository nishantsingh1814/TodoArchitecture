package com.eventx.todoarchitecture.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.eventx.todoarchitecture.data.TodoDatabase;
import com.eventx.todoarchitecture.data.TodoModel;
import com.eventx.todoarchitecture.data.TodoRepository;

import java.util.List;

public class TodoListViewModel extends AndroidViewModel {
    private final LiveData<List<TodoModel>> noteList;
    private TodoRepository todoRepository;


    public TodoListViewModel(@NonNull Application application) {
        super(application);
        todoRepository = new TodoRepository(TodoDatabase.getDatabase(getApplication()));
        noteList = todoRepository.getAllTodos();
    }

    public LiveData<List<TodoModel>> getNoteList() {
        return noteList;
    }

    public void deleteNote(TodoModel todoModel) {
        new DeleteAsyncTask(todoRepository).execute(todoModel);
    }

    class DeleteAsyncTask extends AsyncTask<TodoModel, Void, Void> {
        private TodoRepository todoRepository;

        DeleteAsyncTask(TodoRepository todoRepository) {
            this.todoRepository = todoRepository;
        }


        @Override
        protected Void doInBackground(TodoModel... todoModels) {
            todoRepository.deleteTodo(todoModels[0]);
            return null;
        }
    }
}
