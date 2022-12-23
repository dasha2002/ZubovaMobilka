package com.example.zubovamobilka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NoteDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_NOTE = "NoteDetailsActivity.EXTRA_NOTE";

    private Note note;

    private EditText editText;

    public static void start(Activity caller, Note note) {
        Intent intent = new Intent(caller, NoteDetailsActivity.class);
        if (note != null) {
            intent.putExtra(EXTRA_NOTE, note);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_note);

        editText = findViewById(R.id.editTextTextPersonName3);

        if (getIntent().hasExtra(EXTRA_NOTE)) {
            note = getIntent().getParcelableExtra(EXTRA_NOTE);
            editText.setText(note.text);
        } else {
            note = new Note();
        }
    }


    public void NoteSave(View view) {
        if (editText.getText().length() > 0){
            note.text = editText.getText().toString();
            note.done = false;
            note.timestamp = System.currentTimeMillis();
            if (getIntent().hasExtra(EXTRA_NOTE)){
                App.getInstance().getNoteDao().update(note);
            }
            else{
                App.getInstance().getNoteDao().insert(note);
            }
            finish();
        }
    }
}
