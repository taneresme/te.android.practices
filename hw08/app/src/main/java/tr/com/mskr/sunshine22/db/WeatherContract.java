package tr.com.mskr.sunshine22.db;

import android.provider.BaseColumns;

public class WeatherContract {
    public static class WeatherEntry implements BaseColumns {
        public static final String TABLE_NAME = "Books";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_AUTHOR = "Author";
        public static final String COLUMN_NAME_GENDER= "Gender";
        public static final String COLUMN_NAME_YEAR = "Year";
    }
}
