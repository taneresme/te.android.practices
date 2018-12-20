package com.android.taner.hw10;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.taner.hw10.data.BookContract;

public class MainActivity extends AppCompatActivity {

    Uri bookUri = BookContract.BookEntry.CONTENT_URI;
    ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contentResolver = getContentResolver();

        final EditText bookName = (EditText) findViewById(R.id.bookName);
        final EditText bookAuthor = (EditText) findViewById(R.id.bookAuthor);
        final EditText bookGenre = (EditText) findViewById(R.id.bookGenre);
        final EditText bookYear = (EditText) findViewById(R.id.bookYear);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bookName.getText().toString().equals("") ||
                        bookAuthor.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "You should fill in the fields name and author!",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                String name = bookName.getText().toString();
                String author = bookAuthor.getText().toString();
                String genre = bookGenre.getText().toString();
                Integer year = !bookYear.getText().toString().equals("") ?
                        Integer.parseInt(bookYear.getText().toString())
                        :0;

                if (!bookExits(name)){
                    saveBook(name, author, genre, year);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Already exists",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private Boolean bookExits(String name) {
        String[] projection = new String[]{" COUNT(*) "};
        String selection = "NAME = ?";
        String[] selectionArgs = new String[]{name};
        Cursor query = contentResolver.query(bookUri,
                projection,
                selection,
                selectionArgs,
                null);

        Integer count = 0;
        if (query.getCount() > 0) {
            query.moveToFirst();
            count = query.getInt(0);
        }
        return count > 0;
    }

    private void saveBook(String name, String author, String genre, int year){
        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.COLUMN_NAME, name);
        values.put(BookContract.BookEntry.COLUMN_AUTHOR, author);
        values.put(BookContract.BookEntry.COLUMN_GENRE, genre);
        values.put(BookContract.BookEntry.COLUMN_YEAR, year);

        contentResolver.insert(bookUri, values);

        Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
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
