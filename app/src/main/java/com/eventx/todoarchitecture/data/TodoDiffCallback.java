package com.eventx.todoarchitecture.data;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.eventx.todoarchitecture.Constants;

import java.util.List;

public class TodoDiffCallback extends DiffUtil.Callback {
    public TodoDiffCallback(List<TodoModel> mOldList, List<TodoModel> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }

    private List<TodoModel> mOldList;
    private List<TodoModel> mNewList;

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mNewList.get(newItemPosition).getId() == mOldList.get(oldItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mNewList.get(newItemPosition).equals(mOldList.get(oldItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        TodoModel newTodo = mNewList.get(newItemPosition);
        TodoModel oldTodo = mOldList.get(oldItemPosition);
        Bundle diffBundle = new Bundle();
        if (newTodo.getDate() != oldTodo.getDate()) {
            diffBundle.putLong(Constants.DATE, newTodo.getDate());
        }
        if (newTodo.getDescription() != oldTodo.getDescription()) {
            diffBundle.putString(Constants.DESCRIPTION, newTodo.getDescription());
        }
        if (newTodo.getTitle() != oldTodo.getTitle()) {
            diffBundle.putString(Constants.TITLE, newTodo.getTitle());
        }
        if (newTodo.getRepeat() != oldTodo.getRepeat()) {
            diffBundle.putString(Constants.REPEAT, newTodo.getRepeat());
        }

        if (diffBundle.size() == 0) return null;
        return diffBundle;
    }
}
