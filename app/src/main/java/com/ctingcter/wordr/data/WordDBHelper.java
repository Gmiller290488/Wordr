package com.ctingcter.wordr.data;

/**
 * Created by CTingCTer on 21/04/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ctingcter.wordr.data.WordContract.WordEntry;


/**
 * Database helper for Wordr app.  Manages databse creation and version management.
 */
public class WordDBHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = WordDBHelper.class.getSimpleName();

    // Name of database file
    private static final String DATABASE_NAME = "word.db";

    // Databse version.  If you change the database schema, you must increment the database version.

    private static final int DATABASE_VERSION = 1;

    /**
     *  Constructs a new instance of {@link WordDBHelper}
     * @param context of the app
     */
    public WordDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // This is called when the database is created for the first time.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the words table
        String SQL_CREATE_WORDS_TABLE =  "CREATE TABLE " + WordEntry.TABLE_NAME + " ("
                + WordEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WordEntry.COLUMN_NAME_WORD + " TEXT NOT NULL, "
                + WordEntry.COLUMN_DEFINITION + " TEXT, "
                + WordEntry.COLUMIN_WASLAST + " BOOL NOT NULL DEFAULT FALSE);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_WORDS_TABLE);
    }


    // Called when the database needs to be upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
