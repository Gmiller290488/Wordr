package com.ctingcter.wordr;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ctingcter.wordr.data.WordContract;
import com.ctingcter.wordr.data.WordDBHelper;

public class EditorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mWordEditText;
    private EditText mDefineEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        mWordEditText = (EditText) findViewById(R.id.add_word_editText);
        mDefineEditText = (EditText) findViewById(R.id.add_def_editText);
        Button saveWord = (Button) findViewById(R.id.save_word);
        saveWord.setOnClickListener(this);

    }

    private void insertWord() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String wordString = mWordEditText.getText().toString().trim();
        String defString = mDefineEditText.getText().toString().trim();

        // Create database helper
        WordDBHelper mDBHelper = new WordDBHelper(this);

        // Gets database in write mode
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        // Create a ContentValues object where column names are the keys
        // And the Word "bulwark" and definition will be the values.
        ContentValues values = new ContentValues();
        values.put(WordContract.WordEntry.COLUMN_NAME_WORD, "Bulwark");
        values.put(WordContract.WordEntry.COLUMN_DEFINITION, "a solid wall-like structure raised for defense");
        // Insert a new row and return ID of that row
        long newRowId = db.insert(WordContract.WordEntry.TABLE_NAME, null, values);

        // show a toast message
        if (newRowId == -1) {
            // if row ID is -1 there was an error
            Toast.makeText(this, "Error with inserting word", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise it was successfully inserted
            Toast.makeText(this, "Word saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        insertWord();
    }
}


