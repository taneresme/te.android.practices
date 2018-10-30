package tr.com.mskr.sunshine22;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.net.URL;

import tr.com.mskr.sunshine22.weather.List;

public class DetailActivity extends AppCompatActivity {
    private static final String DEGREE = "\u00b0";
    private static final String IMAGE_URL = "http://openweathermap.org/img/w/";

    private TextView txtDay;
    private TextView txtDayDegree;
    private TextView txtMinMax;
    private TextView txtStatus;
    private ImageView imgWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent   intentThatStartedThisActivity;
        String   forecastStr;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDay = (TextView) findViewById(R.id.txt_day);
        txtDayDegree = (TextView) findViewById(R.id.txt_day_degree);
        txtMinMax = (TextView) findViewById(R.id.txt_min_max);
        txtStatus = (TextView) findViewById(R.id.txt_day_status);
        imgWeather = (ImageView) findViewById(R.id.weather_image);

        intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)){

            forecastStr = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
            Log.v("DetailActivity.onCreate", forecastStr);

            /* Parse data */
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<List> jsonAdapter = moshi.adapter(List.class);
            try {
                List list = jsonAdapter.fromJson(forecastStr);

                String date = list.getReadableDateString();
                long min = Math.round(list.getTemp().getMin());
                long max = Math.round(list.getTemp().getMax());
                long day = Math.round(list.getTemp().getDay());
                String desc = list.getWeather().get(0).getDescription();
                String image = list.getWeather().get(0).getIcon();

                new DownloadImageTask(imgWeather).execute(IMAGE_URL + image + ".png");

                txtDay.setText(date);
                txtMinMax.setText(String.format("%s%s/%s%s", min, DEGREE, max, DEGREE));
                txtDayDegree.setText(String.format("%s%s", day, DEGREE));
                txtStatus.setText(desc);
            } catch (IOException e) {
                Log.v("DetailActivity.onCreate", e.toString());
                txtDay.setText("EXCEPTION OCCURRED");
            }
        }
        else{
            txtDay.setText("NO WEATHER DATA");
        }
    }
}
