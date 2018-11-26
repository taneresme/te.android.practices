package tr.com.mskr.sunshine22;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements ListItemClickListener {

    private static final String   DEGREE              = "\u00b0";
    private static final int      FORECAST_LIST_ITEMS = 12;
    private              String[] FORECASTS           = {
            "Mon, Oct  9: 15"+DEGREE, "Tue, Oct 10: 17"+DEGREE, "Wed, Oct 11: 17"+DEGREE,
            "Thu, Oct 12: 19"+DEGREE, "Fri, Oct 13: 19"+DEGREE, "Sat, Oct 14: 18"+DEGREE,
            "Sun, Oct 15: 19"+DEGREE, "Mon, Oct 16: 22"+DEGREE, "Tue, Oct 17: 18"+DEGREE,
            "Wed, Oct 18: 21"+DEGREE, "Thu, Oct 19: 12"+DEGREE, "Fri, Oct 20: 22"+DEGREE};

    private String                mWeatherAPIKey    = "1b3a6d183e0681e26f960c86ee271000";

    private GreenAdapter          mAdapter;
    private RecyclerView          mNumbersList;
    private DividerItemDecoration mDividerItemDecoration;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mNumbersList                      = (RecyclerView) findViewById(R.id.rv_forecast);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        mNumbersList.setLayoutManager(layoutManager);
        mNumbersList.setHasFixedSize(true);
        mDividerItemDecoration = new DividerItemDecoration(mNumbersList.getContext(), layoutManager.getOrientation());
        mNumbersList.addItemDecoration(mDividerItemDecoration);

        mAdapter = new GreenAdapter(FORECAST_LIST_ITEMS, FORECASTS, this);
        mNumbersList.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        Context context = getApplicationContext();
        int duration    = Toast.LENGTH_SHORT;

        if (id == R.id.action_settings) {

            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.action_help){
            Toast toast = Toast.makeText(context, "Help is clicked", duration);
            toast.show();
        } else if (id == R.id.action_refresh){

            SharedPreferences prefs       = PreferenceManager.getDefaultSharedPreferences(this);
            String            location    = prefs.getString("location", "94043");

            String weatherURLString = "http://api.openweathermap.org/data/2.5/forecast/" +
                    "daily?" +
                    "q="+ location +
                    "&mode=JSON" +
                    "&units=metric" +
                    "&cnt=12" +
                    "&APPID="+mWeatherAPIKey;

            FetchWeatherTask weatherTask = new FetchWeatherTask(mAdapter, this.getApplicationContext());
            weatherTask.execute(weatherURLString);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        Intent detailActivityIntent;

        Log.v("MainActivity.onCreate", "Item#"+Integer.toString(clickedItemIndex));

        detailActivityIntent = new Intent(MainActivity.this, DetailActivity.class);
        detailActivityIntent.putExtra(Intent.EXTRA_TEXT, "Detailed Weather Info");

        startActivity(detailActivityIntent);
    }
}