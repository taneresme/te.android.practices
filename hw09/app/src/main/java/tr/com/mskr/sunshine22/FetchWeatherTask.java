package tr.com.mskr.sunshine22;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import tr.com.mskr.sunshine22.data.WeatherContract;
import tr.com.mskr.sunshine22.data.WeatherContract.LocationEntry;
import tr.com.mskr.sunshine22.data.WeatherContract.WeatherEntry;
import tr.com.mskr.sunshine22.data.WeatherInformation;

public class FetchWeatherTask extends AsyncTask<String, Void, Void> {

    ContentResolver mResolver;
    Context         mContext;

    public FetchWeatherTask(Context context) {
        mContext   = context;
        mResolver  = mContext.getContentResolver();
    }

    public long addLocation(String locationSetting, String cityName, double lat, double lon){

        Log.v("FetchWTask.addLocation", "Start");

        String[] projectedColumns = {LocationEntry._ID};
        String   selectedString   = LocationEntry.COLUMN_LOCATION_SETTING + "=?";
        String[] selectionArgs    = {locationSetting};
        Cursor   locationCursor;
        long locationId;

        locationCursor = mResolver.query(LocationEntry.CONTENT_URI,
                projectedColumns,
                selectedString,
                selectionArgs,
                null);

        if (locationCursor.moveToFirst()){
            int locationIdIndex = locationCursor.getColumnIndex(LocationEntry._ID);
            locationId          = locationCursor.getLong(locationIdIndex);
        }
        else{
            Uri  insertedUri;

            ContentValues locationValues = new ContentValues();
            locationValues.put(LocationEntry.COLUMN_CITY_NAME       ,  cityName);
            locationValues.put(LocationEntry.COLUMN_LOCATION_SETTING,  locationSetting);
            locationValues.put(LocationEntry.COLUMN_COORD_LAT       ,  lat);
            locationValues.put(LocationEntry.COLUMN_COORD_LONG      ,  lon);

            insertedUri = mResolver.insert(LocationEntry.CONTENT_URI, locationValues);
            locationId  = ContentUris.parseId(insertedUri);
        }
        Log.v("FetchWTask.addLocation", "Stop");

        return locationId;
    }

    @Override
    protected Void doInBackground(String... params) {

        HttpURLConnection urlConnection   = null;
        BufferedReader    reader          = null;
        String 		      forecastJsonStr = null;

        String locationQuery            = params[0];
        String format                   = "json";
        String units                    = "metric";
        int numDays                     = 15;

        try {
            final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
            final String QUERY_PARAM       = "q";
            final String FORMAT_PARAM      = "mode";
            final String UNITS_PARAM       = "units";
            final String DAYS_PARAM        = "cnt";
            final String APPID_PARAM       = "APPID";

            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM , locationQuery)
                    .appendQueryParameter(FORMAT_PARAM, format)
                    .appendQueryParameter(UNITS_PARAM , units)
                    .appendQueryParameter(DAYS_PARAM  , Integer.toString(numDays))
                    .appendQueryParameter(APPID_PARAM , "1b3a6d183e0681e26f960c86ee271000")
                    .build();

            URL weatherURL = new URL(builtUri.toString());
            urlConnection  = (HttpURLConnection) weatherURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer     = new StringBuffer();

            if (inputStream != null) {
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() != 0) {
                    forecastJsonStr = buffer.toString();
                }
            }
        } catch (IOException e) {
            Log.e("MainActivity", "Error ", e);
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("MainActivity", "Error closing stream", e);
                }
            }
        }

        try {
            getWeatherDataFromJson(forecastJsonStr, locationQuery);
        } catch (JSONException e) {
            Log.e("FetchWeatherTask", e.getMessage(), e);
        }
        return null;
    }

    private void getWeatherDataFromJson(String forecastJsonStr, String locationSetting)
            throws JSONException {

        final String OWM_LIST           = "list";

        final String OWM_CITY           = "city";
        final String OWM_CITY_NAME      = "name";
        final String OWM_COORD          = "coord";
        final String OWM_LATITUDE       = "lat";
        final String OWM_LONGITUDE      = "lon";

        final String OWM_WEATHER        = "weather";
        final String OWM_TEMPERATURE    = "temp";
        final String OWM_MAX            = "max";
        final String OWM_MIN            = "min";
        final String OWM_DESCRIPTION    = "main";
        final String OWM_DATETIME       = "dt";

        final String OWM_PRESSURE       = "pressure";
        final String OWM_HUMIDITY       = "humidity";
        final String OWM_WINDSPEED      = "speed";
        final String OWM_WIND_DIRECTION = "deg";

        final String OWM_WEATHER_ID     = "id";

        final int    SEC_TO_MILISEC     = 1000;

        JSONObject forecastJson  = new JSONObject(forecastJsonStr);
        JSONArray  weatherArray  = forecastJson.getJSONArray(OWM_LIST);

        JSONObject cityJson      = forecastJson.getJSONObject(OWM_CITY);
        String     cityName      = cityJson.getString(OWM_CITY_NAME);

        JSONObject cityCoord     = cityJson.getJSONObject(OWM_COORD);
        double     cityLat       = cityCoord.getDouble(OWM_LATITUDE);
        double     cityLon       = cityCoord.getDouble(OWM_LONGITUDE);

        long locationId          = addLocation(locationSetting, cityName, cityLat, cityLon);

        if (weatherArray.length() > 5){
            ContentValues[] weatherValuesArray = new ContentValues[weatherArray.length()];
            for(int i = 0; i < weatherArray.length(); i++) {

                WeatherInformation wInfo             = new WeatherInformation();
                JSONObject         dayForecast       = weatherArray.getJSONObject(i);
                JSONObject         weatherObject     = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
                JSONObject         temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);

                wInfo.locationID    = locationId;
                wInfo.dateTime      = dayForecast.getLong(OWM_DATETIME)*SEC_TO_MILISEC;
                wInfo.pressure      = dayForecast.getDouble(OWM_PRESSURE);
                wInfo.humidity      = dayForecast.getInt(OWM_HUMIDITY);
                wInfo.windSpeed     = dayForecast.getDouble(OWM_WINDSPEED);
                wInfo.windDirection = dayForecast.getDouble(OWM_WIND_DIRECTION);

                wInfo.description   = weatherObject.getString(OWM_DESCRIPTION);
                wInfo.weatherID     = weatherObject.getInt(OWM_WEATHER_ID);

                wInfo.high          = temperatureObject.getDouble(OWM_MAX);
                wInfo.low           = temperatureObject.getDouble(OWM_MIN);

                ContentValues weatherValues = new ContentValues();

                weatherValues.put(WeatherEntry.COLUMN_LOC_KEY   , wInfo.locationID);
                weatherValues.put(WeatherEntry.COLUMN_DATE      , wInfo.dateTime);
                weatherValues.put(WeatherEntry.COLUMN_HUMIDITY  , wInfo.humidity);
                weatherValues.put(WeatherEntry.COLUMN_PRESSURE  , wInfo.pressure);
                weatherValues.put(WeatherEntry.COLUMN_WIND_SPEED, wInfo.windSpeed);
                weatherValues.put(WeatherEntry.COLUMN_DEGREES   , wInfo.windDirection);
                weatherValues.put(WeatherEntry.COLUMN_MAX_TEMP  , wInfo.high);
                weatherValues.put(WeatherEntry.COLUMN_MIN_TEMP  , wInfo.low);
                weatherValues.put(WeatherEntry.COLUMN_SHORT_DESC, wInfo.description);
                weatherValues.put(WeatherEntry.COLUMN_WEATHER_ID, wInfo.weatherID);

                weatherValuesArray[i] = getWeatherContentValues(wInfo);
            }

            mResolver.bulkInsert(WeatherEntry.CONTENT_URI, weatherValuesArray);
        }
        else {
            for (int i = 0; i < weatherArray.length(); i++) {

                WeatherInformation wInfo = new WeatherInformation();
                JSONObject dayForecast = weatherArray.getJSONObject(i);
                JSONObject weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
                JSONObject temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);

                wInfo.locationID = locationId;
                wInfo.dateTime = dayForecast.getLong(OWM_DATETIME) * SEC_TO_MILISEC;
                wInfo.pressure = dayForecast.getDouble(OWM_PRESSURE);
                wInfo.humidity = dayForecast.getInt(OWM_HUMIDITY);
                wInfo.windSpeed = dayForecast.getDouble(OWM_WINDSPEED);
                wInfo.windDirection = dayForecast.getDouble(OWM_WIND_DIRECTION);

                wInfo.description = weatherObject.getString(OWM_DESCRIPTION);
                wInfo.weatherID = weatherObject.getInt(OWM_WEATHER_ID);

                wInfo.high = temperatureObject.getDouble(OWM_MAX);
                wInfo.low = temperatureObject.getDouble(OWM_MIN);

                mResolver.insert(WeatherEntry.CONTENT_URI, getWeatherContentValues(wInfo));
            }
        }
    }

    private ContentValues getWeatherContentValues(WeatherInformation wInfo){
        ContentValues weatherValues = new ContentValues();

        weatherValues.put(WeatherEntry.COLUMN_LOC_KEY   , wInfo.locationID);
        weatherValues.put(WeatherEntry.COLUMN_DATE      , wInfo.dateTime);
        weatherValues.put(WeatherEntry.COLUMN_HUMIDITY  , wInfo.humidity);
        weatherValues.put(WeatherEntry.COLUMN_PRESSURE  , wInfo.pressure);
        weatherValues.put(WeatherEntry.COLUMN_WIND_SPEED, wInfo.windSpeed);
        weatherValues.put(WeatherEntry.COLUMN_DEGREES   , wInfo.windDirection);
        weatherValues.put(WeatherEntry.COLUMN_MAX_TEMP  , wInfo.high);
        weatherValues.put(WeatherEntry.COLUMN_MIN_TEMP  , wInfo.low);
        weatherValues.put(WeatherEntry.COLUMN_SHORT_DESC, wInfo.description);
        weatherValues.put(WeatherEntry.COLUMN_WEATHER_ID, wInfo.weatherID);

        return weatherValues;
    }
}