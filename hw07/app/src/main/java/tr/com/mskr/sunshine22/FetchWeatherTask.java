package tr.com.mskr.sunshine22;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

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

import tr.com.mskr.sunshine22.weather.List;
import tr.com.mskr.sunshine22.weather.WeatherApiResult;

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
        } catch (Exception e) {
            Log.e("FetchWeatherTask", e.getMessage(), e);
        }

        // This will only happen if there was an error getting or parsing the forecast.
        return null;
    }

    @Override
    protected void onPostExecute(String[] weatherInfo) {
        super.onPostExecute(weatherInfo);

        for(int i=0; i < weatherInfo.length; i++){
            Log.v("FetchWeatherTask", weatherInfo[i]);
        }
        mWeatherAdapter.setWeatherData(weatherInfo);
    }

    private String[] getWeatherDataFromJson(String forecastJsonStr)
            throws IOException, JSONException {
        final String OWM_LIST = "list";

        JSONObject forecastJson  = new JSONObject(forecastJsonStr);
        JSONArray  weatherArray  = forecastJson.getJSONArray(OWM_LIST);

        String[] resultStrs = new String[weatherArray.length()];

        for(int i = 0; i < weatherArray.length(); i++) {
            JSONObject dayForecast = weatherArray.getJSONObject(i);
            resultStrs[i] = dayForecast.toString();
        }
        return resultStrs;
    }
}