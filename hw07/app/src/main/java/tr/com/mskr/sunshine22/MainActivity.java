package tr.com.mskr.sunshine22;

import android.content.Intent;
import android.os.Bundle;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements ListItemClickListener {

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

        mAdapter = new GreenAdapter(this);
        mNumbersList.setAdapter(mAdapter);

        String weatherAPIKey    = "1b3a6d183e0681e26f960c86ee271000";
        String weatherURLString = "http://api.openweathermap.org/data/2.5/forecast/" +
                "daily?" +
                "q=London" +
                "&mode=JSON" +
                "&units=metric" +
                "&cnt=12" +
                "&APPID="+weatherAPIKey;

        FetchWeatherTask weatherTask = new FetchWeatherTask(mAdapter);
        weatherTask.execute(weatherURLString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

        Intent detailActivityIntent;

        Log.v("MainActivity.onCreate", "Item#"+Integer.toString(clickedItemIndex));

        detailActivityIntent = new Intent(MainActivity.this, DetailActivity.class);
        detailActivityIntent.putExtra(Intent.EXTRA_TEXT, mAdapter.getItem(clickedItemIndex));

        startActivity(detailActivityIntent);
    }
}