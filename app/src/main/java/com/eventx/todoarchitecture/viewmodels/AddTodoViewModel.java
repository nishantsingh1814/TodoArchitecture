package com.eventx.todoarchitecture.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.eventx.todoarchitecture.data.TodoDatabase;
import com.eventx.todoarchitecture.data.TodoModel;
import com.eventx.todoarchitecture.data.TodoRepository;

public class AddTodoViewModel extends AndroidViewModel {
    private TodoRepository todoRepository;

    public AddTodoViewModel(@NonNull Application application) {
        super(application);
        todoRepository = new TodoRepository(TodoDatabase.getDatabase(getApplication()));
    }

    public void addNote(TodoModel todoModel) {
        new AddTodoTask(todoRepository).execute(todoModel);
    }

    private class AddTodoTask extends AsyncTask<TodoModel, Void, Void> {
        private TodoRepository todoRepository;

        AddTodoTask(TodoRepository todoRepository) {
            this.todoRepository = todoRepository;
        }

        @Override
        protected Void doInBackground(TodoModel... todoModels) {
            todoRepository.insertTodo(todoModels[0]);
            return null;
        }
    }
}
