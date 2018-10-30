package tr.com.mskr.sunshine22;

import android.os.AsyncTask;
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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

    GreenAdapter mWeatherAdapter;

    public FetchWeatherTask(GreenAdapter weatherAdapter) {
        mWeatherAdapter = weatherAdapter;
    }

    @Override
    protected String[] doInBackground(String... urlStrings) {

        HttpURLConnection urlConnection   = null;
        BufferedReader    reader          = null;
        String 		      forecastJsonStr = null;

        try {
            URL weatherURL = new URL(urlStrings[0]);
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
            return getWeatherDataFromJson(forecastJsonStr);
        } catch (JSONException e) {
            Log.e("FetchWeatherTask", e.getMessage(), e);
        }

        // This will only happen if there was an error getting or parsing the forecast.
        return null;
    }

    @Override
    protected void onPostExecute(String[] weatherInfo) {
        super.onPostExecute(weatherInfo);

        for(int i=0; i<weatherInfo.length; i++){
            Log.v("FetchWeatherTask", weatherInfo[i]);
        }
        mWeatherAdapter.setWeatherData(weatherInfo);
    }

    public static double getMaxTempForDay(String wJsonStr, int dayIndex)
            throws JSONException {

        JSONObject mainObject = new JSONObject(wJsonStr);
        JSONArray  list       = mainObject.getJSONArray("list");
        String     max_tmp    = list.getJSONObject(dayIndex)
                .getJSONObject("temp")
                .getString("max");

        return Double.parseDouble(max_tmp);
    }

    private String[] getWeatherDataFromJson(String forecastJsonStr)
            throws JSONException {

        final String OWM_LIST           = "list";
        final String OWM_WEATHER        = "weather";
        final String OWM_TEMPERATURE    = "temp";
        final String OWM_MAX            = "max";
        final String OWM_MIN            = "min";
        final String OWM_DESCRIPTION    = "main";
        final String OWM_DATETIME       = "dt";
        final String OWM_PRESSURE       = "pressure";
        final String OWM_HUMIDITY       = "humidity";
        final int    SEC_TO_MILISEC     = 1000;

        JSONObject forecastJson  = new JSONObject(forecastJsonStr);
        JSONArray  weatherArray  = forecastJson.getJSONArray(OWM_LIST);

        String[] resultStrs = new String[weatherArray.length()];

        for(int i = 0; i < weatherArray.length(); i++) {

            String        day;
            String        pressureAndHumidity;
            JSONObject    dayForecast       = weatherArray.getJSONObject(i);
            double        pressure          = dayForecast.getDouble(OWM_PRESSURE);
            double        humidity          = dayForecast.getDouble(OWM_HUMIDITY);
            JSONObject    weatherObject     = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0);
            JSONObject    temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE);

            long   dateTime = dayForecast.getLong(OWM_DATETIME)*SEC_TO_MILISEC;
            String desc     = weatherObject.getString(OWM_DESCRIPTION);

            pressureAndHumidity    = formatPressureAndHumidity(pressure, humidity);
            day                    = getReadableDateString(dateTime);
            resultStrs[i]          = day + " " + pressureAndHumidity;
        }
        return resultStrs;
    }

    private String formatPressureAndHumidity(double pressure, double humidity) {

        String roundedPressure  = new DecimalFormat("#.#").format(pressure);
        long roundedHumidity    = Math.round(humidity);

        String pressureAndHum = String.format("Pr: %s, Hum: %s%%", roundedPressure, roundedHumidity);

        return pressureAndHum;
    }

    private String getReadableDateString(long time){

        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
        return shortenedDateFormat.format(time);
    }

}