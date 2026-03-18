package com.example.lab6_sqlite_database;

import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler db;
    RecyclerView rvNotes;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);
        rvNotes = findViewById(R.id.rvNotes);
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);

        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        loadNotes();

        fabAdd.setOnClickListener(v -> showAddDialog());
    }

    private void loadNotes() {
        List<Note> noteList = db.getAllNotes();
        adapter = new NoteAdapter(noteList, noteId -> {
            db.deleteNote(noteId);
            loadNotes();
        });
        rvNotes.setAdapter(adapter);
    }

    private void showAddDialog() {
        final EditText input = new EditText(this);
        new AlertDialog.Builder(this)
                .setTitle("Thêm ghi chú")
                .setView(input)
                .setPositiveButton("Lưu", (dialog, which) -> {
                    String content = input.getText().toString();
                    if (!content.isEmpty()) {
                        db.addNote(new Note(content));
                        loadNotes();
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }
}