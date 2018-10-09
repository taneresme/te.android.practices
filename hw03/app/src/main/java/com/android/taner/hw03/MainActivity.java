package com.android.taner.hw03;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.android.taner.hw02.R;

public class MainActivity extends AppCompatActivity {

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

        final ToggleButton toggleVisible = findViewById(R.id.toggleVisible);
        final ToggleButton toggleTint = findViewById(R.id.toggleTint);
        final ToggleButton toggleTintMode = findViewById(R.id.toggleTintMode);

        toggleVisible.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setVisibility(toggleVisible.isChecked()
                        ? View.VISIBLE
                        : View.INVISIBLE);
            }
        });
        toggleTint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ImageView imageView = findViewById(R.id.imageView);
                ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor("#ffa500"));
                imageView.setImageTintList(toggleTint.isChecked()
                        ? colorStateList
                        : null);
            }
        });
        toggleTintMode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ImageView imageView = findViewById(R.id.imageView);
                PorterDuff.Mode mode = toggleTintMode.isChecked()
                        ? PorterDuff.Mode.SCREEN
                        : PorterDuff.Mode.MULTIPLY;
                imageView.setImageTintMode(mode);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
