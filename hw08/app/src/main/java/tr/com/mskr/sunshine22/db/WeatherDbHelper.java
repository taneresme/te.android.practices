package tr.com.mskr.sunshine22.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeatherDbHelper extends SQLiteOpenHelper {
    final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE " + WeatherContract.WeatherEntry.TABLE_NAME + " (" +
            WeatherContract.WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            WeatherContract.WeatherEntry.COLUMN_NAME_NAME + " TEXT NOT NULL, " +
            WeatherContract.WeatherEntry.COLUMN_NAME_AUTHOR + " TEXT, " +
            WeatherContract.WeatherEntry.COLUMN_NAME_GENDER + " TEXT, " +
            WeatherContract.WeatherEntry.COLUMN_NAME_YEAR + " INTEGER " +
            " );";

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "weather.db";

    public WeatherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_WEATHER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WeatherContract.WeatherEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
