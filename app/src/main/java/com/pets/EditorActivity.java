package com.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.pets.data.DataContract.pets;
import com.pets.data.PetDbHelper;

public class EditorActivity extends AppCompatActivity {

    private EditText mName;

    private EditText mBreed;

    private EditText mWeight;

    private Spinner mGenderSpinner;

    private int mGender = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mName = findViewById(R.id.edit_pet_name);

        mBreed = findViewById(R.id.edit_pet_breed);

        mWeight = findViewById(R.id.edit_pet_weight);

        mGenderSpinner = findViewById(R.id.spinner_gender);

        /*
        if (posi != 0){
            mName.setText(CatalogActivity.nn.get(posi).getName());
            mBreed.setText(CatalogActivity.nn.get(posi).getFamily());
            mWeight.setText(CatalogActivity.nn.get(posi).getPoids());
            } */

        setupSpinner();
    }

    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mGenderSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.gender_male))) {
                        mGender = pets.GENDER_MALE; // Male
                    } else if (selection.equals(getString(R.string.gender_female))) {
                        mGender = pets.GENDER_FAMMEL; // Female
                    } else {
                        mGender = pets.GENDER_UNKNOW; // Unknown
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mGender = 0; // Unknown
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    private void insertPet(){

        SQLiteDatabase bb = new PetDbHelper(this).getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(pets.COLUMN_NAME, mName.getText().toString().trim());
        value.put(pets.COYUMN_BREED, mBreed.getText().toString().trim());
        value.put(pets.COLUMN_GENDER, mGender);
        value.put(pets.COLUMN_WEIGHT, mWeight.getText().toString().trim());

        long oo = bb.insert(pets.TABLE_NAME, null, value);

        if (oo == -1){
            Toast.makeText(this, "Error in the data", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "you add the pet with data: " + String.valueOf(oo), Toast.LENGTH_SHORT).show();
        }

        Log.v("EditorActivity", String.valueOf(oo));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                insertPet();
                Intent i = new Intent(EditorActivity.this, CatalogActivity.class);
                startActivity(i);
                return true;

            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                return true;

            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}