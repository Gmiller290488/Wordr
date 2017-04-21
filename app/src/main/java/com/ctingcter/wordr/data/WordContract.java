package com.ctingcter.wordr.data;

import android.provider.BaseColumns;
/**
 * Created by CTingCTer on 21/04/2017.
 */

public final class WordContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private WordContract() {}


    public static final class WordEntry implements BaseColumns {
        // Name of database table for words
        public final static String TABLE_NAME = "words";

        // Unique ID number for the word
        public final static String _ID = BaseColumns._ID;

        // The word
        // Type: TEXT
        public final static String COLUMN_NAME_WORD = "word";

        // Definition of the word
        // Type: TEXT
        public final static String COLUMN_DEFINITION = "definition";

        // Was it displayed last?
        // Type: Boolean
        public final static String COLUMIN_WASLAST = "wasLast";
    }
}
