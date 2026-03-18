package com.example.lab6_sqlite_database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes;
    private OnNoteClickListener listener;

    public interface OnNoteClickListener {
        void onDeleteClick(int noteId);
    }

    public NoteAdapter(List<Note> notes, OnNoteClickListener listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.tvNote.setText(note.getContent());
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(note.getId()));
    }

    @Override
    public int getItemCount() { return notes.size(); }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNote;
        ImageButton btnDelete;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNote = itemView.findViewById(R.id.tvNote);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}