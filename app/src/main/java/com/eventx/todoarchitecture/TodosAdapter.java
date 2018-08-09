package com.eventx.todoarchitecture;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eventx.todoarchitecture.data.NoteModel;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private Context mContext;
    private List<NoteModel> mNoteList;
    private View.OnLongClickListener onLongClickListener;


    public NotesAdapter(Context mContext, List<NoteModel> mNoteList, View.OnLongClickListener onLongClickListener) {
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
        NoteModel noteModel = mNoteList.get(position);
        holder.date.setText(noteModel.getDate() + "");
        holder.description.setText(noteModel.getDescription());
        holder.title.setText(noteModel.getTitle());
        holder.repeat.setText(noteModel.getRepeat());
        holder.itemView.setTag(noteModel);
        holder.itemView.setOnLongClickListener(onLongClickListener);
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
    public void addNote(List<NoteModel> noteModels){
        this.mNoteList= noteModels;
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
