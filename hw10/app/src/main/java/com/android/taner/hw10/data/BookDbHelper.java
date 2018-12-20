package com.android.taner.hw10.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.taner.hw10.data.BookContract.BookEntry;

public class BookDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int    DATABASE_VERSION = 2;
    public  static final String DATABASE_NAME    = "book.db";

    public BookDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE " + BookEntry.TABLE_NAME + " (" +
                BookEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                BookEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                BookEntry.COLUMN_AUTHOR + " TEXT NOT NULL, " +
                BookEntry.COLUMN_GENRE + " TEXT, " +
                BookEntry.COLUMN_YEAR + " INTEGER " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_LOCATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        // If you want to update the schema without wiping data, commenting out the next 2 lines
        // should be your top priority before modifying this method.

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BookEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
