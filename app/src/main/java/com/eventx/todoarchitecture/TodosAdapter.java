package com.eventx.todoarchitecture;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eventx.todoarchitecture.data.TodoModel;

import java.util.List;

public class TodosAdapter extends RecyclerView.Adapter<TodosAdapter.NoteViewHolder> {

    private Context mContext;
    private List<TodoModel> mNoteList;
    private View.OnLongClickListener onLongClickListener;


    public TodosAdapter(Context mContext, List<TodoModel> mNoteList, View.OnLongClickListener onLongClickListener) {
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
        holder.date.setText(todoModel.getDate() + "");
        holder.description.setText(todoModel.getDescription());
        holder.title.setText(todoModel.getTitle());
        holder.repeat.setText(todoModel.getRepeat());
        holder.itemView.setTag(todoModel);
        holder.itemView.setOnLongClickListener(onLongClickListener);
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
    public void addNote(List<TodoModel> todoModels){
        this.mNoteList= todoModels;
        notifyDataSetChanged();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView date;
        private TextView time;
        private TextView repeat;


        NoteViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.keep_title);
            description = itemView.findViewById(R.id.keep_description);
            date = itemView.findViewById(R.id.keep_date);
            time = itemView.findViewById(R.id.keep_time);
            repeat = itemView.findViewById(R.id.keep_repeat);
        }
    }
}
