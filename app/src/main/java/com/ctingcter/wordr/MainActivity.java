package com.ctingcter.wordr;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ctingcter.wordr.data.WordContract;
import com.ctingcter.wordr.data.WordContract.WordEntry;
import com.ctingcter.wordr.data.WordDBHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class MainActivity extends AppCompatActivity {

    // Database helper that will provide access to the database
    private WordDBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayDatabaseInfo();
        mDBHelper = new WordDBHelper(this);

        // Set up add word button to launch EditorActivity
        Button addWord = (Button) findViewById(R.id.add_word);
        addWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                        startActivity(intent);
            }
        });
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        WordDBHelper mDBHelper = new WordDBHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + WordContract.WordEntry.TABLE_NAME, null);
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // pets table in the database).
            TextView displayView = (TextView) findViewById(R.id.word);
            displayView.setText("Number of rows in words database table: " + cursor.getCount());
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on "insert random word" menu option
            case R.id.action_insert_random_word:
                insertWord();
                displayDatabaseInfo();
                return true;
            // Respond to a click on the "Delete all words" menu option
            case R.id.action_delete_all_entries:
                // Fill out this part later
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertWord() {
        // gets the databse in write mode
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys
        // And the Word "bulwark" and definition will be the values.
        ContentValues values = new ContentValues();
        values.put(WordEntry.COLUMN_NAME_WORD, "Bulwark");
        values.put(WordEntry.COLUMN_DEFINITION, "a solid wall-like structure raised for defense");

        long newRowId = db.insert(WordEntry.TABLE_NAME, null, values);
    }
}

