package com.eventx.todoarchitecture;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eventx.todoarchitecture.data.TodoModel;

import java.util.List;

public class TodosAdapter extends PagedListAdapter<TodoModel, TodosAdapter.NoteViewHolder> {

    private Context mContext;
    private List<TodoModel> mNoteList;
    private View.OnLongClickListener onLongClickListener;


    public TodosAdapter(Context mContext, List<TodoModel> mNoteList, View.OnLongClickListener onLongClickListener) {
        super(TodoModel.DIFF_CALLBACK);
        this.mContext = mContext;
        this.mNoteList = mNoteList;
        this.onLongClickListener = onLongClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        TodoModel todoModel = mNoteList.get(position);
        if (todoModel != null) {
            holder.bindTo(todoModel);
        }else{
            holder.clear();
        }
        holder.itemView.setOnLongClickListener(onLongClickListener);
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    public void addNote(List<TodoModel> todoModels) {
        this.mNoteList = todoModels;
        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView date;
        private TextView repeat;


        NoteViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.keep_title);
            description = itemView.findViewById(R.id.keep_description);
            date = itemView.findViewById(R.id.keep_date);
            repeat = itemView.findViewById(R.id.keep_repeat);
        }

        void clear() {
            title.invalidate();
            date.invalidate();
            repeat.invalidate();
            description.invalidate();
        }

        void bindTo(TodoModel todoModel) {
            date.setText(todoModel.getDate() + "");
            description.setText(todoModel.getDescription());
            title.setText(todoModel.getTitle());
            repeat.setText(todoModel.getRepeat());
            itemView.setTag(todoModel);
        }
    }

}
