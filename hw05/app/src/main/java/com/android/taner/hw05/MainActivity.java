package com.android.taner.hw05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.taner.hw05.movie.Movie;
import com.android.taner.hw05.movie.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Movie> movies = new ArrayList<>();
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;

    private void createMovies(){
        movies.add(new Movie(R.drawable.venom, "Venom", 2018));
        movies.add(new Movie(R.drawable.avengers, "Avengers: Infinity War", 2018));
        movies.add(new Movie(R.drawable.mission_impossible, "Mission: Impossible", 2018));
        movies.add(new Movie(R.drawable.meg, "Meg", 2018));
        movies.add(new Movie(R.drawable.deadpool_2, "Deadpool 2", 2018));
        movies.add(new Movie(R.drawable.yenilmezler, "Avengers", 2012));
        movies.add(new Movie(R.drawable.thor, "Thor: Ragnarok", 2017));
        movies.add(new Movie(R.drawable.ant_man, "Ant-Man and Wasp", 2018));
        movies.add(new Movie(R.drawable.gokdelen, "Skyscrapper", 2018));
        movies.add(new Movie(R.drawable.deadpool_1, "Deadpool", 2016));
        movies.add(new Movie(R.drawable.rampage, "Rampage", 2018));
        movies.add(new Movie(R.drawable.black_panther, "Black Panther", 2018));
        movies.add(new Movie(R.drawable.orumcek_adam, "Spider Man: Homecoming", 2017));
        movies.add(new Movie(R.drawable.kaptan_amerika, "Captain America: Civil War", 2016));
        movies.add(new Movie(R.drawable.wonder_woman, "Wonder Woman", 2017));
        movies.add(new Movie(R.drawable.justice_league, "Justice League", 2017));
        movies.add(new Movie(R.drawable.john_wick, "John Wick", 2014));
        movies.add(new Movie(R.drawable.logan, "Logan", 2017));
        movies.add(new Movie(R.drawable.ant_man_0, "Ant Man", 2015));
        movies.add(new Movie(R.drawable.han_solo, "Solo: A Star Wars Story", 2018));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /* Let's create movies. */
        createMovies();

        recyclerView = findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        movieAdapter = new MovieAdapter(movies);
        recyclerView.setAdapter(movieAdapter);
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
