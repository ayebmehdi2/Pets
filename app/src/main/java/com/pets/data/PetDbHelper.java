package com.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PetDbHelper extends SQLiteOpenHelper{


    private static final int VERSION = 1;
    private static final String NAME_DB = "pet.dp";

    public PetDbHelper(Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    private static final String TBL_CREATE = "CREATE TABLE "+ DataContract.pets.TABLE_NAME + "(" +
            DataContract.pets._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DataContract.pets.COLUMN_NAME + " TEXT NOT NULL, " +
            DataContract.pets.COYUMN_BREED + " TEXT, "+
            DataContract.pets.COLUMN_GENDER+" INTEGER NOT NULL, " +
            DataContract.pets.COLUMN_WEIGHT +" INTEGER NOT NULL DEFAULT 0);";

    private static final String TBL_DELET = "DROP TABLE " + DataContract.pets.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TBL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(TBL_DELET);
        sqLiteDatabase.execSQL(TBL_CREATE);
    }
}
