package com.pets;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.pets.data.DataContract.pets;
import com.pets.data.PetDbHelper;
import java.util.ArrayList;

public class CatalogActivity extends AppCompatActivity {

    SQLiteDatabase ins;
    PetDbHelper mDB;
    TextView yy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        yy = findViewById(R.id.list);

        final MediaPlayer mm =MediaPlayer.create(CatalogActivity.this, R.raw.aa);
        ImageView fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mm.start();
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        mDB = new PetDbHelper(CatalogActivity.this);

        ins = mDB.getReadableDatabase();

        String[] column = new String[] {pets._ID,
                pets.COLUMN_NAME,
                pets.COYUMN_BREED,
                pets.COLUMN_GENDER,
                pets.COLUMN_WEIGHT};


        @SuppressLint("Recycle")
        Cursor cursor = getContentResolver().query(pets.CONTENT_URI,
                column,
                null,
                null,
                null);

        StringBuilder st = new StringBuilder();

        try {
            assert cursor != null;
            while (cursor.moveToNext()){

                    String name = cursor.getString(cursor.getColumnIndex(pets.COLUMN_NAME));
                    String breed = cursor.getString(cursor.getColumnIndex(pets.COYUMN_BREED));
                    int gender = cursor.getInt(cursor.getColumnIndex(pets.COLUMN_GENDER));
                    String weight = String.valueOf(cursor.getInt(cursor.getColumnIndex(pets.COLUMN_WEIGHT)));
                    String genderText = "";
                    if (gender == 0) {
                        genderText = "Unknow";
                    } else if (gender == 1) {
                        genderText = "Male";
                    } else if (gender == 2) {
                        genderText = "Female";
                    }
                    st.append(name).append(" - ").append(breed).append(" - ").append(genderText).append(" - ").append(weight).append("\n\n");
            }
        }finally {
            cursor.close();
        }

        yy.setText(st.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    public void insertData(){
        mDB = new PetDbHelper(CatalogActivity.this);
        ins = mDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(pets.COLUMN_NAME, "lucy");
        values.put(pets.COYUMN_BREED, "cat");
        values.put(pets.COLUMN_GENDER, pets.COLUMN_GENDER);
        values.put(pets.COLUMN_WEIGHT, 1);

        ins.insert(pets.TABLE_NAME, null, values);
        displayDatabaseInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertData();
                return true;
            case R.id.action_delete_all_entries:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
